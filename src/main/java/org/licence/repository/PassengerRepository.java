package org.licence.repository;

import org.licence.entity.Passenger;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ionut on 24.03.2016.
 */
public interface PassengerRepository extends JpaRepository<Passenger, Long> {
}
