package com.epam.learning.messageorientedmiddleware.kafka.service;

import com.epam.learning.messageorientedmiddleware.kafka.model.Distance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService {

    private static final Logger log = LoggerFactory.getLogger(ConsumeService.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.groupid}")
    public void listenGroup(@Payload Distance distance,
                               @Header(KafkaHeaders.RECEIVED_PARTITION_ID) int partition,
                                @Header(KafkaHeaders.OFFSET) int offset) {
        log.info("Received: {} from partition {} and offset = {}", distance, partition, offset);
    }

}
