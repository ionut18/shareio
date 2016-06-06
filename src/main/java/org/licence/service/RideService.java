package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
import org.licence.entity.Driver;
import org.licence.entity.Ride;
import org.licence.model.RideModel;
import org.licence.model.SearchRideModel;
import org.licence.repository.AppUserRepository;
import org.licence.repository.CarRepository;
import org.licence.repository.DriverRepository;
import org.licence.repository.RideRepository;
import org.licence.util.RideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by ionut on 14.04.2016.
 */
@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    @Autowired
    private AppUserRepository userRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private MessageService commentService;

    @Autowired
    private DriverRepository driverRepository;

    @Autowired
    private RideMapper rideMapper;

    public void saveRideAndDriver(Ride ride, String username) {
        saveRide(ride);

        saveDriver(ride, username);
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    private void saveDriver(Ride ride, String username) {
        AppUser user = userRepository.findByUsername(username);
        //// TODO: if exists car
        Car car = carRepository.findByIdAppUser(user.getIdAppUser());

        Driver driver = new Driver();
        driver.setIdAppUser(user.getIdAppUser());
        driver.setIdCar(car.getIdCar());
        driver.setIdRide(ride.getIdRide());
        driver.setRating("Good");

        driverRepository.save(driver);
    }

    public List<RideModel> getAllRides(SearchRideModel searchRideModel, String username) {

        List<Ride> rides;

        if(username != null) {
            rides = getUserRides(searchRideModel, username);
        } else {
            rides = getRides(searchRideModel);
        }

        List<RideModel> rideModels = new ArrayList<>();

        for(Ride ride : rides) {
            Driver driver = driverRepository.getByIdRide(ride.getIdRide());
            AppUser user = userRepository.findOne(driver.getIdAppUser());
            Car car = carRepository.findOne(driver.getIdCar());

            RideModel rideModel = rideMapper.RideModelMapper(ride, driver, user, car);
            if(rideModel != null) {
                rideModels.add(rideModel);
            }
        }

        return rideModels;
    }

    private List<Ride> getRides(SearchRideModel searchRideModel) {
        List<Ride> rides;
        if(searchRideModel.getDeparture() != null && searchRideModel.getDestination() != null) {
            if (!searchRideModel.getDeparture().equals("") && !searchRideModel.getDestination().equals("")) {
                rides = rideRepository.findByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThan(searchRideModel.getDeparture(), searchRideModel.getDestination(), new Date());
            } else {
                if (!searchRideModel.getDeparture().equals("")) {
                    rides = rideRepository.findByDepartureIgnoreCaseAndDepartureTimeGreaterThan(searchRideModel.getDeparture(), new Date());
                } else {
                    if (!searchRideModel.getDestination().equals("")) {
                        rides = rideRepository.findByDestinationIgnoreCaseAndDepartureTimeGreaterThan(searchRideModel.getDestination(), new Date());
                    } else {
                        rides = rideRepository.findByDepartureTimeGreaterThan(new Date());
                    }
                }
            }
        } else {
            rides = rideRepository.findByDepartureTimeGreaterThan(new Date());
        }
        return rides;
    }

    private List<Ride> getUserRides(SearchRideModel searchRideModel, String username) {
        List<Ride> rides = new ArrayList<>();

        AppUser user = userRepository.findByUsername(username);

        List<Driver> drivers = driverRepository.findByIdAppUser(user.getIdAppUser());

        for(Driver driver : drivers) {
            Ride ride = rideRepository.findOne(driver.getIdRide());
            if(ride.getDepartureTime().compareTo(new Date()) > 0) {
                rides.add(ride);
            }
        }

        return rides;
    }

    public Ride getRideById(Long id) {
        return rideRepository.getOne(id);
    }

    public void makeReservation(Long idRide, String passengerUserName) {
        Ride ride = rideRepository.findOne(idRide);

        Driver driver = driverRepository.findByIdRide(idRide);

        AppUser driverUser = userRepository.findOne(driver.getIdAppUser());

        if(!driverUser.getUsername().equals(passengerUserName) && passengerUserName != null) {
            commentService.sendDefaultMessageToDriver(driverUser.getUsername(), passengerUserName, ride);
        } else {
            // TODO: The same user and driver
        }
    }
}
