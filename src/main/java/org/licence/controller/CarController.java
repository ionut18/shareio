package org.licence.controller;

import org.licence.entity.Car;
import org.licence.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ionut on 24.03.2016.
 */
@Controller
@RequestMapping("/car")
public class CarController extends BaseController {

    @Autowired
    private CarService carService;

    @RequestMapping("/")
    public String getCarView(Model model) {

        if(carService.getCarByUsername(getUserName()) != null) {
            model.addAttribute("car", carService.getCarByUsername(getUserName()));
            return "car/list";
        }
        else {
            return "redirect:/car/add";
        }
    }

    @RequestMapping("/add")
    public String addCarView(Model model) {
        model.addAttribute("car", new Car());
        return "car/add";
    }

    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public String addCar(@ModelAttribute("car") Car car) {
        carService.saveCar(car, getUserName());
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}")
    public String edit(@PathVariable Long id, Model model) {
        if(carService.getCarById(id) != null) {
            model.addAttribute("car", carService.getCarById(id));
            return "/car/add";
        }
        else {
            return "redirect:/";
        }
    }

}