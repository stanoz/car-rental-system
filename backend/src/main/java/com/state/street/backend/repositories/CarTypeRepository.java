package com.state.street.backend.repositories;

import com.state.street.backend.model.entity.CarType;
import com.state.street.backend.model.enums.CarCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarTypeRepository extends JpaRepository<CarType, Long> {
    Optional<CarType> findByType(CarCategory type);
}
