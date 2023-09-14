package com.epam.learning.messageorientedmiddleware.kafka.service.impl;

import com.epam.learning.messageorientedmiddleware.kafka.model.Position;
import com.epam.learning.messageorientedmiddleware.kafka.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private ProduceService produceService;

    @Override
    public Position sendVehicle(Position position) {
        produceService.send(position);
        return position;
    }

}
