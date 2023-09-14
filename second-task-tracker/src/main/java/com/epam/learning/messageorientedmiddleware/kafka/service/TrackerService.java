package com.epam.learning.messageorientedmiddleware.kafka.service;

import com.epam.learning.messageorientedmiddleware.kafka.model.Distance;
import com.epam.learning.messageorientedmiddleware.kafka.model.Position;
import com.epam.learning.messageorientedmiddleware.kafka.model.Vehicle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class TrackerService {

    private static Map<Long, Vehicle> vehicles = new HashMap<>();

    @Autowired
    private ProduceService produceService;

    public Distance processPosition(Position position) {
        if (!vehicles.containsKey(position.getId())) {
            Vehicle vehicle = new Vehicle(position);
            vehicles.put(vehicle.getId(), vehicle);
        }
        Vehicle vehicle = vehicles.get(position.getId());
        Distance distance = new Distance(position.getId(), vehicle.calcDistance(position));
        produceService.send(distance);
        return distance;
    }

}
