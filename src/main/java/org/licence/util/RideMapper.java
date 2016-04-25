package org.licence.util;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
import org.licence.entity.Driver;
import org.licence.entity.Ride;
import org.licence.model.RideModel;
import org.springframework.stereotype.Component;

/**
 * Created by ionut on 25.04.2016.
 */
@Component
public class RideMapper {

    public RideModel RideModelMapper(Ride ride, Driver driver, AppUser user, Car car) {
        RideModel rideModel = new RideModel();
        rideModel.setDriverName(user.getFirstName() + " " + user.getLastName().charAt(0));
        rideModel.setCarName(car.getManufacturer() + " " + car.getModel());
        rideModel.setDeparture(ride.getDeparture());
        rideModel.setDestination(ride.getDestination());
        rideModel.setDepartureTime(ride.getDepartureTime().toString());
        rideModel.setDistance(ride.getKilometers());
        rideModel.setRating(driver.getRating());
        rideModel.setAvailableInsteadStorage(ride.getAvailableInsteadStorage());
        rideModel.setAvailableSeats(ride.getAvailableSeats());
        return rideModel;
    }

}
