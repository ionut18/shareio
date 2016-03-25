package org.licence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ionut on 24.03.2016.
 */
@Controller
@RequestMapping("/car")
public class CarController {

    @RequestMapping("/add")
    public String addCarView() {
        return "car/add";
    }

}
