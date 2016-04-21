package org.licence.service;

import org.licence.entity.Ride;
import org.licence.repository.RideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by ionut on 14.04.2016.
 */
@Service
public class RideService {

    @Autowired
    private RideRepository rideRepository;

    public void saveRide(Ride ride) {
        rideRepository.save(ride);
    }

    public List<Ride> getAllRides() {
        return rideRepository.findAll();
    }

    public Ride getRideById(Long id) {
        return rideRepository.getOne(id);
    }

}
