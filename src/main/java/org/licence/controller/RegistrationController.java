package org.licence.controller;

import org.licence.entity.AppUser;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by ionut on 22.03.2016.
 */
@Controller
@RequestMapping("/register")
public class RegistrationController {

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public void register(@ModelAttribute("user")AppUser appUser) {
        System.out.println(appUser.toString());
    }
}
