package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.Car;
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
    private UserService userService;

    public void saveCar(Car car, String username) {
        AppUser user = userService.getUserByUsername(username);
        car.setIdAppUser(user.getIdAppUser());
        carRepository.save(car);
    }

    public Car getCarByUsername(String username) {
        AppUser user = userService.getUserByUsername(username);
        return carRepository.findByIdAppUser(user.getIdAppUser());
    }

    public Car getCarById(Long id) {
        return carRepository.getOne(id);
    }

    public Car getCarByIdAppUser(Long idAppUser) {
        return carRepository.findByIdAppUser(idAppUser);
    }
}
