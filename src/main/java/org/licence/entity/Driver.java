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
public class Driver {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_DRIVER_GEN")
    @SequenceGenerator(name = "SEQ_DRIVER_GEN", sequenceName = "SEQ_DRIVER", allocationSize = 1, initialValue = 1)
    private Long idDriver;

//    @OneToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "id_app_user")
    private Long idAppUser;

//    @OneToMany(targetEntity = Car.class)
    @JoinColumn(name = "id_car")
    private Long idCar;

//    @OneToMany(targetEntity = Ride.class)
    @JoinColumn(name = "id_ride")
    private Long idRide;

    private String rating;

}
