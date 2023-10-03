package com.epam.learning.messageorientedmiddleware.kafka;

import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.junit.Test;
import org.testcontainers.containers.KafkaContainer;
import org.testcontainers.shaded.com.google.common.collect.ImmutableMap;
import org.testcontainers.utility.DockerImageName;

import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.*;

public class KafkaTest {

    @Test
    public void testKafka() throws Exception {
        try (KafkaContainer kafka = new KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:latest"))) {

            kafka.start();

            String bootstrapServers = kafka.getBootstrapServers();
            int partitions = 3;
            int rf = 1;
            String topicName = "BuiTopic";
            String groupId = "BuiGroup";

            MyProducer producer = new MyProducer(bootstrapServers);
            MyConsumer consumer = new MyConsumer(bootstrapServers, groupId, topicName);

            try (AdminClient adminClient = AdminClient.create(ImmutableMap.of(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers))) {
                Collection<NewTopic> topics = Collections.singletonList(new NewTopic(topicName, partitions, (short) rf));
                adminClient.createTopics(topics).all().get(30, TimeUnit.SECONDS);

                producer.produce(topicName, "id_1", "Hello Bui");

                ConsumerRecords<String, String> records = consumer.consume(5000);

                assertThat(records)
                        .hasSize(1)
                        .extracting(ConsumerRecord::topic, ConsumerRecord::key, ConsumerRecord::value)
                        .containsExactly(tuple(topicName, "id_1", "Hello Bui"));

            } finally {
                producer.closeProducer();
                consumer.closeConsumer();
            }
        }
    }

}