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
}
