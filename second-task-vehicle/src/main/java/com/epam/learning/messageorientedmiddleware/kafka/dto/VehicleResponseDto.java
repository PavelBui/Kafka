package com.epam.learning.messageorientedmiddleware.kafka.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleResponseDto {

    private Long id;

    private String latitude;

    private String longitude;

}
