package org.licence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by ionut on 04.06.2016.
 */
@Entity
@Getter
@Setter
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MESSAGE_GEN")
    @SequenceGenerator(name = "SEQ_MESSAGE_GEN", sequenceName = "SEQ_MESSAGE", allocationSize = 1, initialValue = 1)
    private Long idMessage;

    @JoinColumn(name = "id_ride")
    private Long idRide;

    private String driver;

    private String passenger;

    private String sender;

    private String receiver;

    private String text;

    private Date date;

    private Boolean done;

}
