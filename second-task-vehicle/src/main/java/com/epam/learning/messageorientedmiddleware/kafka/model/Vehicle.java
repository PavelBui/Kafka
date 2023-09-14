package com.epam.learning.messageorientedmiddleware.kafka.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {

    private Long id;

    private float latitude;

    private float longitude;

    @Override
    public String toString() {
        return "Vehicle{" +
                "id=" + id +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id) && latitude == vehicle.latitude && longitude ==vehicle.longitude;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude);
    }

}