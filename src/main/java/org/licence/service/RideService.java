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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

    public Page<RideModel> getAllRides(Pageable pageable, SearchRideModel searchRideModel) {

        Page<Ride> rides;
        Long noRides;
        if(searchRideModel.getDeparture() != null && searchRideModel.getDestination() != null) {
            if (!searchRideModel.getDeparture().equals("") && !searchRideModel.getDestination().equals("")) {
                rides = rideRepository.findByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThan(pageable, searchRideModel.getDeparture(),
                        searchRideModel.getDestination(), new Date());
                noRides = rideRepository.countByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThan(searchRideModel.getDeparture(),
                        searchRideModel.getDestination(), new Date());
            } else {
                if (!searchRideModel.getDeparture().equals("")) {
                    rides = rideRepository.findByDepartureIgnoreCaseAndDepartureTimeGreaterThan(pageable, searchRideModel.getDeparture(), new Date());
                    noRides = rideRepository.countByDepartureIgnoreCaseAndDepartureTimeGreaterThan(searchRideModel.getDeparture(), new Date());
                } else {
                    if (!searchRideModel.getDestination().equals("")) {
                        rides = rideRepository.findByDestinationIgnoreCaseAndDepartureTimeGreaterThan(pageable, searchRideModel.getDestination(), new Date());
                        noRides = rideRepository.countByDestinationIgnoreCaseAndDepartureTimeGreaterThan(searchRideModel.getDestination(), new Date());
                    } else {
                        rides = rideRepository.findByDepartureTimeGreaterThan(pageable, new Date());
                        noRides = rideRepository.countByDepartureTimeGreaterThan(new Date());
                    }
                }
            }
        } else {
            rides = rideRepository.findByDepartureTimeGreaterThan(pageable, new Date());
            noRides = rideRepository.countByDepartureTimeGreaterThan(new Date());
        }

        List<RideModel> rideModels = createRideModelList(rides);

        return new PageImpl<>(rideModels, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()),
                noRides);
    }

    private List<RideModel> createRideModelList(Page<Ride> rides) {

        List<RideModel> rideModels = new ArrayList<>();

        for(Ride ride : rides) {
            addToRideModelList(rideModels, ride);
        }
        return rideModels;
    }

    private void addToRideModelList(List<RideModel> rideModels, Ride ride) {
        Driver driver = driverRepository.getByIdRide(ride.getIdRide());
        AppUser user = userRepository.findOne(driver.getIdAppUser());
        Car car = carRepository.findOne(driver.getIdCar());

        RideModel rideModel = rideMapper.RideModelMapper(ride, driver, user, car);

        if (rideModel != null) {
            rideModels.add(rideModel);
        }
    }

    public Page<RideModel> getAllRidesForUser(Pageable pageable, String username) {

        AppUser user = userRepository.findByUsername(username);

        List<Ride> rides = getUserRides(user);

        List<Ride> pageRides = new ArrayList<>();

        Integer offset = pageable.getPageSize() * pageable.getPageNumber();
        Integer limit = pageable.getPageSize() * pageable.getPageNumber() + pageable.getPageSize();

        for (int i = offset; i < rides.size() && i < limit; i++) {
            pageRides.add(rides.get(i));
        }

        List<RideModel> rideModels = createRideModelListForUser(pageRides);


        return new PageImpl<>(rideModels, new PageRequest(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort()),
                rides.size());
    }

    private List<Ride> getUserRides(AppUser user) {

        List<Ride> rides = new ArrayList<>();

        List<Driver> drivers = driverRepository.findByIdAppUser(user.getIdAppUser());

        for(Driver driver : drivers) {
            Ride ride = rideRepository.findByIdRideAndDepartureTimeGreaterThan(driver.getIdRide(), new Date());
            if(ride!= null) {
                rides.add(ride);
            }
        }

        return rides;
    }

    private List<RideModel> createRideModelListForUser(List<Ride> rides) {
        List<RideModel> rideModels = new ArrayList<>();

        for(Ride ride : rides) {
            addToRideModelList(rideModels, ride);
        }
        return rideModels;
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
