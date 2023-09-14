package com.epam.learning.messageorientedmiddleware.kafka.converter;

import com.epam.learning.messageorientedmiddleware.kafka.dto.PositionRequestDto;
import com.epam.learning.messageorientedmiddleware.kafka.model.Position;
import org.springframework.beans.BeanUtils;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PositionRequestDtoToPositionConverter implements Converter<PositionRequestDto, Position> {

    @Override
    public Position convert(PositionRequestDto positionRequestDto) {
        Position position = new Position();
        BeanUtils.copyProperties(positionRequestDto, position);
        position.setLatitude(Double.parseDouble(positionRequestDto.getLatitude()));
        position.setLongitude(Double.parseDouble(positionRequestDto.getLongitude()));
        return position;
    }
}
