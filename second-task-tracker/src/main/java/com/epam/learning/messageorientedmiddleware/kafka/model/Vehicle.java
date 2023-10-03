package com.epam.learning.messageorientedmiddleware.kafka.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
public class Vehicle {

    private static final double KM_PER_DEGREE_LATITUDE = 111.134861111;
    private static final double KM_PER_DEGREE_LONGITUDE = 111.321277778;

    private Long id;

    private double latitude;

    private double longitude;

    private double mileage;

    public Vehicle(Position position) {
        this.id = position.getId();
        this.latitude = position.getLatitude();
        this.longitude = position.getLongitude();
        this.mileage = 0;
    }

    public double calcDistance(Position position) {
        double deltaLatitude = Math.abs(latitude - position.getLatitude());
        double deltaLongitude = Math.abs(longitude - position.getLongitude());
        double averageLatitude = (latitude + position.getLatitude()) / 2;

        mileage = mileage +
                deltaLatitude * KM_PER_DEGREE_LATITUDE +
                deltaLongitude * KM_PER_DEGREE_LONGITUDE * Math.cos(Math.PI * averageLatitude / 180);

        latitude = position.getLatitude();
        longitude = position.getLongitude();

        return mileage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return id.equals(vehicle.id) && latitude == vehicle.latitude && longitude == vehicle.longitude && mileage == vehicle.mileage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, latitude, longitude, mileage);
    }
}
