package org.licence.repository;

import org.licence.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ionut on 24.03.2016.
 */
public interface RideRepository extends JpaRepository<Ride, Long> {
}
