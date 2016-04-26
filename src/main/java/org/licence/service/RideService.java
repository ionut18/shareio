package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
import org.licence.entity.Driver;
import org.licence.entity.Ride;
import org.licence.model.RideModel;
import org.licence.repository.AppUserRepository;
import org.licence.repository.CarRepository;
import org.licence.repository.DriverRepository;
import org.licence.repository.RideRepository;
import org.licence.util.RideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
    private DriverRepository driverRepository;

    public void saveRide(Ride ride, String username) {
        rideRepository.save(ride);

        AppUser user = userRepository.findByUsername(username);
        Car car = carRepository.getByIdAppUser(user.getIdAppUser());

        Driver driver = new Driver();
        driver.setIdAppUser(user.getIdAppUser());
        driver.setIdCar(car.getIdCar());
        driver.setIdRide(ride.getIdRide());
        driver.setRating("Good");

        driverRepository.save(driver);
    }

    public List<RideModel> getAllRides() {
        List<Ride> rides = rideRepository.findAll();
        List<RideModel> rideModels = new ArrayList<>();
        RideMapper rideMapper = new RideMapper();
        for(Ride ride : rides) {
            Driver driver = driverRepository.getByIdRide(ride.getIdRide());
            AppUser user = userRepository.findOne(driver.getIdAppUser());
            Car car = carRepository.findOne(driver.getIdCar());
            rideModels.add(rideMapper.RideModelMapper(ride, driver, user, car));
        }

        return rideModels;
    }

    public Ride getRideById(Long id) {
        return rideRepository.getOne(id);
    }

}
