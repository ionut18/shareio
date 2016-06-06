package org.licence.repository;

import org.licence.entity.Ride;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

/**
 * Created by ionut on 24.03.2016.
 */
public interface RideRepository extends JpaRepository<Ride, Long> {

    List<Ride> findByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThan(String departure, String destination, Date departureTime);

    List<Ride> findByDepartureIgnoreCaseAndDepartureTimeGreaterThan(String departure, Date departureTime);

    List<Ride> findByDestinationIgnoreCaseAndDepartureTimeGreaterThan(String destination, Date departureTime);

    List<Ride> findByDepartureTimeGreaterThan(Date departureTime);

}
