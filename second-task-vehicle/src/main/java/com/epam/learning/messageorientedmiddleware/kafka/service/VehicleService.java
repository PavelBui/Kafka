package com.epam.learning.messageorientedmiddleware.kafka.service;

import com.epam.learning.messageorientedmiddleware.kafka.model.Position;

public interface VehicleService {

    Position sendVehicle(Position position);

}
