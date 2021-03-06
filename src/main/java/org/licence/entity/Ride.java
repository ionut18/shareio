package org.licence.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ionut on 24.03.2016.
 */
@Entity
@Getter
@Setter
@ToString
public class Ride {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_RIDE_GEN")
    @SequenceGenerator(name = "SEQ_RIDE_GEN", sequenceName = "SEQ_RIDE", allocationSize = 1, initialValue = 1)
    private Long idRide;

    private String departure;

    private String destination;

    private Date departureTime;

    private Integer price;

    private Integer distance;

    private Integer availableSeats;

    private String availableInsteadStorage;


}
