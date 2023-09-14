package com.epam.learning.messageorientedmiddleware.kafka.service.impl;

import com.epam.learning.messageorientedmiddleware.kafka.model.Vehicle;
import com.epam.learning.messageorientedmiddleware.kafka.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private ProduceService produceService;

    @Override
    public Vehicle sendVehicle(Vehicle vehicle) {
        produceService.send(vehicle);
        return vehicle;
    }

}
