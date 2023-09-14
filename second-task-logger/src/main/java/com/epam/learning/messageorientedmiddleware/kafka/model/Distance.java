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
public class Distance {

    private Long id;

    private double mileage;

    @Override
    public String toString() {
        return "Distance {" +
                "id=" + id +
                ", mileage=" + mileage +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Distance distance = (Distance) o;
        return id.equals(distance.id) && mileage == distance.mileage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, mileage);
    }

}