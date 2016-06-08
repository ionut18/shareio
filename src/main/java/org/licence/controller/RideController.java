package org.licence.controller;

import org.licence.entity.Ride;
import org.licence.model.PageWrapper;
import org.licence.model.RideModel;
import org.licence.model.SearchRideModel;
import org.licence.service.RideService;
import org.licence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/**
 * Created by ionut on 24.03.2016.
 */
@Controller
@RequestMapping("/ride")
public class RideController extends BaseController {

    @Autowired
    RideService rideService;

    @Autowired
    UserService userService;

    @RequestMapping("/list")
    public String getRides(@ModelAttribute("searchRideModel") SearchRideModel searchRideModel, @RequestParam(value = "page.number", required = false) String page, Model model) {
        if(page == null) {
            page = "1";
        }
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1, 10, Sort.Direction.ASC, "departureTime");
        PageWrapper<RideModel> pageWrapper = new PageWrapper<>(rideService.getAllRides(pageable, searchRideModel), "/ride/list");
        model.addAttribute("rideListPage", pageWrapper);
        return "ride/list";
    }

    @RequestMapping("/myList")
    public String getUserRides(@ModelAttribute("searchRideModel") SearchRideModel searchRideModel, @RequestParam(value = "page.number", required = false) String page, Model model) {
        if(page == null) {
            page = "1";
        }
        PageWrapper<RideModel> pageWrapper;
        Pageable pageable = new PageRequest(Integer.parseInt(page)-1, 10, Sort.Direction.ASC, "idRide");
        if(userService.getUserRole(getUserName()).equals("ROLE_CLIENT")) {
            pageWrapper = new PageWrapper<>(rideService.getAllRidesForUser(pageable, getUserName()), "/ride/myList");
        } else /*if(userService.getUserRole(getUserName()).equals("ROLE_ADMIN"))*/ {
            pageWrapper = new PageWrapper<>(rideService.getAllRides(pageable, searchRideModel), "/ride/myList");
        }
        model.addAttribute("rideListPage", pageWrapper);
        return "ride/list";
    }

    @RequestMapping("/add")
    public String addRideView(Model model) {
        model.addAttribute(new Ride());
        return "ride/add";
    }

    @RequestMapping(value = "/add/", method = RequestMethod.POST)
    public String addRide(@ModelAttribute("ride") Ride ride) {
        rideService.saveRideAndDriver(ride, getUserName());
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

    @RequestMapping(value = "/reserve/{id}")
    public String makeReservation(@PathVariable Long id) {
        rideService.makeReservation(id, getUserName());
        return "redirect:/ride/list";
    }
}
