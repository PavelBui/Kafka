package com.epam.learning.messageorientedmiddleware.kafka;

import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;

public class Main {

    private static final Logger log = LoggerFactory.getLogger(Main.class.getSimpleName());

    public static void main(String[] args) {

        String bootstrapServers = "localhost:29092";
        String topicName = "BuiTopic";
        String groupId = "BuiGroup";

        MyProducer producer = new MyProducer(bootstrapServers);
        MyConsumer consumer = new MyConsumer(bootstrapServers, groupId, topicName);

        final Thread mainThread = Thread.currentThread();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            public void run() {
                log.info("Detected a shutdown");
                consumer.wakeup();
                try {
                    mainThread.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        try {
            Random random = new Random();
            while(true) {
                int randomBatch = random.nextInt(5);
                for (int i = 0; i < randomBatch; i++) {
                    int randomInt = random.nextInt(1000);
                    producer.produce(topicName, "id_" + randomInt, "Hello " + randomInt);
                    Thread.sleep(500);
                }
                Thread.sleep(2000);
                consumer.consume(1000);
            }

        } catch (WakeupException e) {
            log.info("Consumer is starting to shut down");
        } catch (Exception e) {
            log.error("Unknown exception", e);
        } finally {
            producer.closeProducer();
            consumer.closeConsumer();
            log.info("Producer and consumer are shut down");
        }

    }
}
