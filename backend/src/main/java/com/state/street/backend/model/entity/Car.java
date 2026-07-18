package com.state.street.backend.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Objects;

@Entity(name = "cars")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(AccessLevel.NONE)
    private Long id;

    @ManyToOne(optional = false)
    @JoinColumn(name = "car_type_id")
    private CarType type;

    private int inStock = 0;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String licensePlate;

    @Column(nullable = false)
    private BigDecimal costPerDay;

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Car car)) return false;
        return inStock == car.inStock && Objects.equals(id, car.id) && Objects.equals(type, car.type) && Objects.equals(brand, car.brand) && Objects.equals(licensePlate, car.licensePlate) && Objects.equals(costPerDay, car.costPerDay);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, type, inStock, brand, licensePlate, costPerDay);
    }
}
