package com.epam.learning.messageorientedmiddleware.kafka.converter;

import com.epam.learning.messageorientedmiddleware.kafka.dto.VehicleRequestDto;
import com.epam.learning.messageorientedmiddleware.kafka.model.Vehicle;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class VehicleRequestDtoToVehicleConverter implements Converter<VehicleRequestDto, Vehicle> {

    @Override
    public Vehicle convert(VehicleRequestDto vehicleRequestDto) {
        Vehicle vehicle = new Vehicle();
        BeanUtils.copyProperties(vehicleRequestDto, vehicle);
        vehicle.setLatitude(Float.parseFloat(vehicleRequestDto.getLatitude()));
        vehicle.setLongitude(Float.parseFloat(vehicleRequestDto.getLongitude()));
        return vehicle;
    }
}
