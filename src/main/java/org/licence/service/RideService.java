package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
import org.licence.entity.Driver;
import org.licence.entity.Ride;
import org.licence.model.RideModel;
import org.licence.model.SearchRideModel;
import org.licence.repository.DriverRepository;
import org.licence.repository.RideRepository;
import org.licence.util.RideMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
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
    private DriverRepository driverRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private CarService carService;

    @Autowired
    private MessageService messageService;

    @Autowired
    private RideMapper rideMapper;

    @Transactional
    public void saveRideAndDriver(Ride ride, String username) {
        saveRide(ride);

        saveDriver(ride, username);
    }

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    private void saveDriver(Ride ride, String username) {
        AppUser user = userService.getUserByUsername(username);
        try {
            Car car = carService.getCarByIdAppUser(user.getIdAppUser());
            if(driverRepository.findByIdRide(ride.getIdRide()) == null) {
                Driver driver = new Driver();
                driver.setIdAppUser(user.getIdAppUser());
                driver.setIdCar(car.getIdCar());
                driver.setIdRide(ride.getIdRide());
                driver.setRating("Good");

                driverRepository.save(driver);
            }
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    public Page<RideModel> getAllRides(Pageable pageable, SearchRideModel searchRideModel) {

        Page<Ride> rides;
        Long noRides;
        if(searchRideModel.getDeparture() != null && searchRideModel.getDestination() != null) {
            if (!searchRideModel.getDeparture().equals("") && !searchRideModel.getDestination().equals("")) {
                rides = rideRepository.findByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(pageable, searchRideModel.getDeparture(),
                        searchRideModel.getDestination(), new Date(), 0);
                noRides = rideRepository.countByDepartureIgnoreCaseAndDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(searchRideModel.getDeparture(),
                        searchRideModel.getDestination(), new Date(), 0);
            } else {
                if (!searchRideModel.getDeparture().equals("")) {
                    rides = rideRepository.findByDepartureIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(pageable, searchRideModel.getDeparture(), new Date(), 0);
                    noRides = rideRepository.countByDepartureIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(searchRideModel.getDeparture(), new Date(), 0);
                } else {
                    if (!searchRideModel.getDestination().equals("")) {
                        rides = rideRepository.findByDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(pageable, searchRideModel.getDestination(), new Date(), 0);
                        noRides = rideRepository.countByDestinationIgnoreCaseAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(searchRideModel.getDestination(), new Date(), 0);
                    } else {
                        rides = rideRepository.findByDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(pageable, new Date(), 0);
                        noRides = rideRepository.countByDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(new Date(), 0);
                    }
                }
            }
        } else {
            rides = rideRepository.findByDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(pageable, new Date(), 0);
            noRides = rideRepository.countByDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(new Date(), 0);
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
        AppUser user = userService.getUserById(driver.getIdAppUser());
        Car car = carService.getCarById(driver.getIdCar());

        RideModel rideModel = rideMapper.RideModelMapper(ride, driver, user, car);

        if (rideModel != null) {
            rideModels.add(rideModel);
        }
    }

    public Page<RideModel> getAllRidesForUser(Pageable pageable, String username) {

        AppUser user = userService.getUserByUsername(username);

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
            Ride ride = rideRepository.findByIdRideAndDepartureTimeGreaterThanAndAvailableSeatsGreaterThan(driver.getIdRide(), new Date(), 0);
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

        AppUser driverUser = userService.getUserById(driver.getIdAppUser());

        if(!driverUser.getUsername().equals(passengerUserName) && passengerUserName != null) {
            messageService.sendDefaultMessageToDriver(driverUser.getUsername(), passengerUserName, ride);
        } else {
            // TODO: The same user and driver
        }
    }
}
