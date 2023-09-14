package com.epam.learning.messageorientedmiddleware.kafka.controller.impl;

import com.epam.learning.messageorientedmiddleware.kafka.controller.VehicleController;
import com.epam.learning.messageorientedmiddleware.kafka.dto.PositionRequestDto;
import com.epam.learning.messageorientedmiddleware.kafka.model.Position;
import com.epam.learning.messageorientedmiddleware.kafka.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VehicleControllerImpl implements VehicleController {

    @Autowired
    private VehicleService vehicleService;
    @Autowired
    private Converter<PositionRequestDto, Position> positionRequestDtoToPositionConverter;

    public ResponseEntity<String> sendVehicle(@RequestBody PositionRequestDto positionRequestDto) {
        try {
            Position vehicle = vehicleService.sendVehicle(positionRequestDtoToPositionConverter.convert(positionRequestDto));
            if (vehicle.getId() < 1) {
                return ResponseEntity.badRequest().body("Bad id (should be more than 0");
            }
            if (vehicle.getLatitude() < - 90 || vehicle.getLatitude() > 90 || vehicle.getLongitude() < -180 || vehicle.getLongitude() > 180) {
                return ResponseEntity.badRequest().body("Bad coordinates (latitude should be inside [-90, 90]; longitude should be inside [-180, 180]");
            }
            return ResponseEntity.ok(vehicle.toString());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Non processable vehicle");
        }
    }

}
