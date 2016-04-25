package org.licence.repository;

import org.licence.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ionut on 24.03.2016.
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver getByIdRide(Long id);

}
