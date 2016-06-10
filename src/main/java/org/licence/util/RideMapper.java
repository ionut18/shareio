package org.licence.util;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
import org.licence.entity.Driver;
import org.licence.entity.Ride;
import org.licence.model.RideModel;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * Created by ionut on 25.04.2016.
 */
@Component
public class RideMapper {

    public RideModel RideModelMapper(Ride ride, Driver driver, AppUser user, Car car) {

        RideModel rideModel = new RideModel();

        rideModel.setIdRide(ride.getIdRide());
        rideModel.setDriverName(user.getFirstName() + " " + user.getLastName().charAt(0));
        rideModel.setCarName(car.getManufacturer() + " " + car.getModel());
        rideModel.setCarComfort(car.getComfort());
        rideModel.setDeparture(ride.getDeparture());
        rideModel.setDestination(ride.getDestination());

        try {
            SimpleDateFormat wantedDate = new SimpleDateFormat("dd MMM hh:mm a");
            SimpleDateFormat givenDate = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.S");
            Date parsedDate = givenDate.parse(ride.getDepartureTime().toString());
            String date = wantedDate.format(parsedDate);
            rideModel.setDepartureTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        rideModel.setDistance(ride.getKilometers());
        rideModel.setPrice(ride.getPrice());
        rideModel.setRating(driver.getRating());
        rideModel.setAvailableInsteadStorage(ride.getAvailableInsteadStorage());
        rideModel.setAvailableSeats(ride.getAvailableSeats());
        rideModel.setUser(user);

        return rideModel;
    }

}
