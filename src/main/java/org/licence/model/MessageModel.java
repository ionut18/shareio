package org.licence.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.licence.entity.AppUser;

/**
 * Created by ionut on 05.06.2016.
 */
@Getter
@Setter
@ToString
public class MessageModel {

    private Long idMessage;

    private Long idRide;

    private String driver;

    private String sender;

    private String receiver;

    private String text;

    private String date;

    private String action;

    private AppUser user;
}
