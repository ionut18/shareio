package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.Message;
import org.licence.entity.Passenger;
import org.licence.entity.Ride;
import org.licence.model.MessageModel;
import org.licence.repository.MessageRepository;
import org.licence.repository.PassengerRepository;
import org.licence.util.MessageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ionut on 04.06.2016.
 */
@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    UserService userService;

    @Autowired
    PassengerRepository passengerRepository;

    @Autowired
    RideService rideService;

    @Autowired
    MessageMapper messageMapper;

    public void sendDefaultMessageToDriver(String driver, String passenger, Ride ride) {

        if(messageRepository.findByIdRideAndPassenger(ride.getIdRide(), passenger).size() == 0) {
            String defaultMessage = "Sunt interesat de calatoria ta de la " + ride.getDeparture() + " la " +
                    ride.getDestination() + " din data de " + ride.getDepartureTime().toString().substring(0,10);

            Message message = new Message();

            message.setIdRide(ride.getIdRide());
            message.setDriver(driver);
            message.setPassenger(passenger);
            message.setSender(passenger);
            message.setReceiver(driver);
            message.setText(defaultMessage);
            message.setDate(new Date());

            messageRepository.save(message);
        } else {
            // TODO: A fost facuta deja o rezervare
        }
    }

    public List<MessageModel> getAllMessagesForUser(String username) {
        List<Message> messages = messageRepository.findByReceiverOrderByDateDesc(username);
        List<MessageModel> messageModels = new ArrayList<>();
        for(Message message : messages) {
            messageModels.add(messageMapper.messageModelMapper(message));
        }
        return messageModels;
    }

    public void handleResponse(MessageModel messageModel) {

        Ride ride = rideService.getRideById(messageModel.getIdRide());
        String defaultMessage;

        if(messageModel.getAction().equals("approved")) {
            defaultMessage = "Te-am acceptat in calatoria de la " + ride.getDeparture() + " la " +
                    ride.getDestination() + " din data de " + ride.getDepartureTime().toString().substring(0, 10);

            decrementNoSeats(messageModel.getIdRide());
            savePassenger(messageModel.getReceiver(), messageModel.getIdRide());
        } else {
            defaultMessage = "Nu te-am acceptat in calatoria de la " + ride.getDeparture() + " la " +
                    ride.getDestination() + " din data de " + ride.getDepartureTime().toString().substring(0, 10);
        }

        changeMessageAction(messageModel.getIdMessage());

        Message message = new Message();
        message.setIdRide(messageModel.getIdRide());
        message.setDriver(messageModel.getDriver());
        message.setPassenger(messageModel.getReceiver());
        message.setSender(messageModel.getDriver());
        message.setReceiver(messageModel.getReceiver());
        message.setText(defaultMessage);
        message.setDate(new Date());

        messageRepository.save(message);
    }

    private void savePassenger(String receiver, Long idRide) {

        AppUser user = userService.getUserByUsername(receiver);

        Passenger passenger = new Passenger();
        passenger.setIdRide(idRide);
        passenger.setIdAppUser(user.getIdAppUser());

        passengerRepository.save(passenger);
    }

    private void changeMessageAction(Long idMessage) {
        Message message = messageRepository.findOne(idMessage);

        message.setDone(true);

        messageRepository.save(message);
    }

    private void decrementNoSeats(Long idRide) {
        Ride ride = rideService.getRideById(idRide);

        ride.setAvailableSeats(ride.getAvailableSeats()-1);

        rideService.saveRide(ride);
    }
}
