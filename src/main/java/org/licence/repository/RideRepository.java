package org.licence.repository;

import org.licence.entity.Ride;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;

/**
 * Created by ionut on 24.03.2016.
 */
public interface RideRepository extends JpaRepository<Ride, Long> {

    Page<Ride> findByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThan(Pageable pageable, String departure, String destination, Date departureTime);

    Long countByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThan(String departure, String destination, Date departureTime);

    Page<Ride> findByDepartureIgnoreCaseAndDepartureTimeGreaterThan(Pageable pageable, String departure, Date departureTime);

    Long countByDepartureIgnoreCaseAndDepartureTimeGreaterThan(String departure, Date departureTime);

    Page<Ride> findByDestinationIgnoreCaseAndDepartureTimeGreaterThan(Pageable pageable, String destination, Date departureTime);

    Long countByDestinationIgnoreCaseAndDepartureTimeGreaterThan(String destination, Date departureTime);

    Page<Ride> findByDepartureTimeGreaterThan(Pageable pageable, Date departureTime);

    Ride findByIdRideAndDepartureTimeGreaterThan(Long idRide, Date departureTime);

    Long countByDepartureTimeGreaterThan(Date departureTime);

}
