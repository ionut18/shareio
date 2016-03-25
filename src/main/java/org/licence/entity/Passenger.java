package org.licence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

/**
 * Created by ionut on 24.03.2016.
 */
@Entity
@Getter
@Setter
public class Passenger {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_PASSENGER_GEN")
    @SequenceGenerator(name = "SEQ_PASSENGER_GEN", sequenceName = "SEQ_PASSENGER", allocationSize = 1, initialValue = 1)
    private Long idPassenger;

//    @OneToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "id_app_user")
    private Long idAppUser;

//    @ManyToMany(targetEntity = Ride.class)
    @JoinColumn(name = "id_ride")
    private Long idRide;

}
