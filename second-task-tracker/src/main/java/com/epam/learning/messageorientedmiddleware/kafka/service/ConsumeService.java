package com.epam.learning.messageorientedmiddleware.kafka.service;

import com.epam.learning.messageorientedmiddleware.kafka.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class ConsumeService {

    private static final Logger log = LoggerFactory.getLogger(ConsumeService.class);

    @Autowired
    private TrackerService trackerService;

    @KafkaListener(topics = "${spring.kafka.topic.input.name}", groupId = "${spring.kafka.groupid}")
    public void listenGroup(Position position) {
        log.info("Received: {}", position);
        trackerService.processPosition(position);
    }

}
