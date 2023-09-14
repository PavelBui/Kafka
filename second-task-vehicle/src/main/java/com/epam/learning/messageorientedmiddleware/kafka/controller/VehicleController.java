package com.epam.learning.messageorientedmiddleware.kafka.controller;

import com.epam.learning.messageorientedmiddleware.kafka.dto.PositionRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/vehicle")
public interface VehicleController {

    @GetMapping
    ResponseEntity<String> sendVehicle(@RequestBody PositionRequestDto vehicleRequestDto);

}
