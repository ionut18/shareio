package org.licence.controller;

import org.licence.entity.AppUser;
import org.licence.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

/**
 * Created by ionut on 22.03.2016.
 */
@Controller
public class RegistrationController {

    @Autowired
    UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute("user")AppUser appUser) {
        if (userService.saveUser(appUser)) {
            return "redirect:/";
        }
        else {
            return "redirect:/login";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request) throws ServletException {
        request.logout();
        return "redirect:/login";
    }
}
