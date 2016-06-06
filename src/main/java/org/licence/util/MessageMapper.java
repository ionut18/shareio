package org.licence.util;

import org.licence.entity.AppUser;
import org.licence.entity.Message;
import org.licence.model.MessageModel;
import org.licence.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by ionut on 05.06.2016.
 */
@Component
public class MessageMapper {

    @Autowired
    AppUserRepository userRepository;

    public MessageModel messageModelMapper(Message message) {

        MessageModel messageModel = new MessageModel();

        messageModel.setIdMessage(message.getIdMessage());
        messageModel.setIdRide(message.getIdRide());
        messageModel.setDriver(message.getDriver());
        messageModel.setSender(message.getSender());
        messageModel.setReceiver(message.getReceiver());
        messageModel.setText(message.getText());

        try {
            SimpleDateFormat wantedDate = new SimpleDateFormat("dd MMM hh:mm a");
            SimpleDateFormat givenDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date parsedDate = givenDate.parse(message.getDate().toString());
            String formatDate = wantedDate.format(parsedDate);
            messageModel.setDate(formatDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(message.getDone() != null) {
            if(message.getDone()) {
                messageModel.setAction("done");
            }
        }

        messageModel.setUser(userRepository.findByUsername(message.getSender()));

        return messageModel;
    }

}
