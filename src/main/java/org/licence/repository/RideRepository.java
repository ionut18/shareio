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

    Page<Ride> findByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(Pageable pageable, String departure, String destination, Date departureTime, Integer noSeats);

    Long countByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(String departure, String destination, Date departureTime, Integer noSeats);

    Page<Ride> findByDepartureIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(Pageable pageable, String departure, Date departureTime, Integer noSeats);

    Long countByDepartureIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(String departure, Date departureTime, Integer noSeats);

    Page<Ride> findByDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(Pageable pageable, String destination, Date departureTime, Integer noSeats);

    Long countByDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(String destination, Date departureTime, Integer noSeats);

    Page<Ride> findByDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(Pageable pageable, Date departureTime, Integer noSeats);

    Ride findByIdRideAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(Long idRide, Date departureTime, Integer noSeats);

    Long countByDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(Date departureTime, Integer noSeats);

}
