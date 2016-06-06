package org.licence.repository;

import org.licence.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ionut on 24.03.2016.
 */
public interface CarRepository extends JpaRepository<Car, Long> {

    Car findByIdAppUser(Long id);

}
