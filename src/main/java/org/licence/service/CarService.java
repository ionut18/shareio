package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
import org.licence.repository.AppUserRepository;
import org.licence.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ionut on 02.04.2016.
 */
@Service
public class CarService {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private AppUserRepository userRepository;

    public void saveCar(Car car, String username) {
        AppUser user = userRepository.findByUsername(username);
        car.setIdAppUser(user.getIdAppUser());
        carRepository.save(car);
    }

    public Car getCarByUsername(String username) {
        AppUser user = userRepository.findByUsername(username);
        return carRepository.getByIdAppUser(user.getIdAppUser());
    }

    public Car getCarById(Long id) {
        return carRepository.getOne(id);
    }
}
