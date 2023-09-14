package com.epam.learning.messageorientedmiddleware.kafka.service.impl;

import com.epam.learning.messageorientedmiddleware.kafka.model.Vehicle;
import org.apache.kafka.clients.admin.NewTopic;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class ProduceService {

    private static final Logger log = LoggerFactory.getLogger(ProduceService.class);

    @Autowired
    private KafkaTemplate<String, Vehicle> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    public void send(Vehicle vehicle) {
        CompletableFuture<SendResult<String, Vehicle>> future = kafkaTemplate.send(topic.name(), vehicle.getId().toString(), vehicle).completable();
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent {} with partition = {} and offset = {}", vehicle, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send {} due to {}", vehicle, ex.getMessage());
            }
        });
    }

}