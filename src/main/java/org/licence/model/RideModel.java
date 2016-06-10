package org.licence.model;

import lombok.Getter;
import lombok.Setter;
import org.licence.entity.AppUser;

/**
 * Created by ionut on 25.04.2016.
 */
@Getter
@Setter
public class RideModel {

    private Long idRide;

    private String driverName;

    private String carName;

    private String carComfort;

    private String rating;

    private String departure;

    private String destination;

    private String departureTime;

    private Integer price;

    private Integer distance;

    private Integer availableSeats;

    private String availableInsteadStorage;

    private AppUser user;
}
