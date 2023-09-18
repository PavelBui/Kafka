package com.epam.learning.messageorientedmiddleware.kafka;

import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

public class MyProducer {

    private static final Logger log = LoggerFactory.getLogger(MyProducer.class.getSimpleName());

    private static final Properties properties = new Properties();
    private static KafkaProducer<String, String> producer;

    public MyProducer(String bootstrapServers) {
        properties.setProperty("bootstrap.servers", bootstrapServers);
        properties.setProperty("key.serializer", StringSerializer.class.getName());
        properties.setProperty("value.serializer", StringSerializer.class.getName());
        properties.setProperty("acks", "all");
        properties.setProperty("min.insync.replicas", "2");
        properties.setProperty("retries", "2147483647");
        properties.setProperty("max.in.flight.requests", "5");
        properties.setProperty("enable.idempotence", "true");
        producer = new KafkaProducer<>(properties);
    }

    public void produce(String topic, String key, String message) {

        ProducerRecord<String, String> producerRecord = new ProducerRecord<>(topic, key, message);

        producer.send(producerRecord, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if (e == null) {
                    log.info("Received new metadata: key = " + key + ", partition = " + recordMetadata.partition() + ", offset = " + recordMetadata.offset());
                } else {
                    log.error("Error while producing: " + e);
                }
            }
        });

    }

    public void closeProducer() {
        producer.flush();
        producer.close();
    }

}