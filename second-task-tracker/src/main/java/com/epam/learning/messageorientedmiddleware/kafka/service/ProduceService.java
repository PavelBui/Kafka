package com.epam.learning.messageorientedmiddleware.kafka.service;

import com.epam.learning.messageorientedmiddleware.kafka.model.Distance;
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
    private KafkaTemplate<String, Distance> kafkaTemplate;

    @Autowired
    private NewTopic topicInput;

    public void send(Distance distance) {
        CompletableFuture<SendResult<String, Distance>> future = kafkaTemplate.send(topicInput.name(), distance.getId().toString(), distance).completable();
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                log.info("Sent {} with partition = {} and offset = {}", distance, result.getRecordMetadata().partition(), result.getRecordMetadata().offset());
            } else {
                log.error("Unable to send {} due to {}", distance, ex.getMessage());
            }
        });
    }

}