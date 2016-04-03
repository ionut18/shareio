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
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CAR_GEN")
    @SequenceGenerator(name = "SEQ_CAR_GEN", sequenceName = "SEQ_CAR", allocationSize = 1, initialValue = 1)
    private Long idCar;

    private String manufacturer;

    private String model;

    private String comfort;

    @JoinColumn(name = "id_app_user")
    private Long idAppUser;
}
