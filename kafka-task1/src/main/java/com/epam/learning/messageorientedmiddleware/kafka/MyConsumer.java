package com.epam.learning.messageorientedmiddleware.kafka;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.List;
import java.util.Properties;

public class MyConsumer {

    private static final Logger log = LoggerFactory.getLogger(MyConsumer.class.getSimpleName());

    private static final Properties properties = new Properties();
    private static KafkaConsumer<String, String> consumer;

    public MyConsumer(String bootstrapServers, String groupId, String topic) {
        properties.setProperty("bootstrap.servers", bootstrapServers);
        properties.setProperty("key.deserializer", StringDeserializer.class.getName());
        properties.setProperty("value.deserializer", StringDeserializer.class.getName());
        properties.setProperty("group.id", groupId);
        properties.setProperty("enable.auto.commit", "true");
        properties.setProperty("auto.commit.interval.ms", "101");
        properties.setProperty("auto.offset.reset", "earliest");
        consumer = new KafkaConsumer<>(properties);
        consumer.subscribe(List.of(topic));
    }

    public ConsumerRecords<String, String> consume(long duration) {

        ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(duration));
        for (ConsumerRecord<String, String> record: records) {
            log.info("Message received: key = " + record.key() + ", value = " + record.value() + ", partition = " + record.partition() + ", offset = " + record.offset());
        }

        return records;

    }

    public void wakeup() {
        consumer.wakeup();
    }

    public void closeConsumer() {
        consumer.close();
    }

}
