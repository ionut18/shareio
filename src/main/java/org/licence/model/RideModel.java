package org.licence.model;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by ionut on 25.04.2016.
 */
@Getter
@Setter
public class RideModel {

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
}
