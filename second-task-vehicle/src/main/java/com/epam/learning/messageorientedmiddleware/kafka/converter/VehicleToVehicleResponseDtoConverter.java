package com.epam.learning.messageorientedmiddleware.kafka.converter;

import com.epam.learning.messageorientedmiddleware.kafka.dto.VehicleResponseDto;
import com.epam.learning.messageorientedmiddleware.kafka.model.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleToVehicleResponseDtoConverter implements Converter<Vehicle, VehicleResponseDto> {

    @Override
    public VehicleResponseDto convert(Vehicle vehicle) {
        VehicleResponseDto vehicleResponseDto = new VehicleResponseDto();
        BeanUtils.copyProperties(vehicle, vehicleResponseDto);
        vehicleResponseDto.setLatitude(String.valueOf(vehicle.getLatitude()));
        vehicleResponseDto.setLongitude(String.valueOf(vehicle.getLongitude()));
        return vehicleResponseDto;
    }
}
