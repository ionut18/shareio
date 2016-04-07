package org.licence.controller;

import org.licence.entity.Ride;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ionut on 24.03.2016.
 */
@Controller
@RequestMapping("/ride")
public class RideController {

    @RequestMapping("/")
    public String view(Model model) {
        model.addAttribute(new Ride());
        return "ride/add";
    }

}
