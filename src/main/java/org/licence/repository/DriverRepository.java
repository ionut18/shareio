package org.licence.repository;

import org.licence.entity.Driver;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by ionut on 24.03.2016.
 */
public interface DriverRepository extends JpaRepository<Driver, Long> {

    Driver getByIdRide(Long id);

    Driver findByIdRide(Long idRide);

    List<Driver> findByIdAppUser(Long idAppUser);

    Long countByIdAppUser(Long idAppUser);
}
