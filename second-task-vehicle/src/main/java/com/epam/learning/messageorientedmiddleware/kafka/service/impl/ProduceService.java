package com.epam.learning.messageorientedmiddleware.kafka.service.impl;

import com.epam.learning.messageorientedmiddleware.kafka.model.Position;
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
    private KafkaTemplate<String, Position> kafkaTemplate;

    @Autowired
    private NewTopic topic;

    public void send(Position position) {
        CompletableFuture<SendResult<String, Position>> future = kafkaTemplate.send(topic.name(), position.getId().toString(), position).completable();
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent {} with partition = {} and offset = {}", position, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send {} due to {}", position, ex.getMessage());
            }
        });
    }

}