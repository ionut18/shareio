package org.licence.controller;

import org.licence.entity.Ride;
import org.licence.service.RideService;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/ride")
public class RideController {
//    TODO: De vazut cum pun user mereu pe model(mambo)
    @Autowired
    RideService rideService;

    @RequestMapping("/list")
    public String getRides(Model model) {
        model.addAttribute("rideList", rideService.getAllRides());
        return "ride/list";
    }

    @RequestMapping("/add")
    public String addRideView(Model model) {
        model.addAttribute(new Ride());
        return "ride/add";
    }

    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public String addRide(@ModelAttribute("ride") Ride ride) {
        rideService.saveRide(ride);
        return "redirect:/";
    }

    @RequestMapping(value = "/{id}")
    public String edit(@PathVariable Long id, Model model) {
        if(rideService.getRideById(id) != null) {
            model.addAttribute("ride", rideService.getRideById(id));
            return "/ride/add";
        }
        else {
            return "redirect:/";
        }
    }
}
