package org.licence.controller;

import org.licence.model.SearchRideModel;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ionut on 09.03.2016.
 */
@Controller
public class IndexController extends BaseController {

    @RequestMapping("/")
    public String getIndex(Model model){
        model.addAttribute("searchRideModel", new SearchRideModel());
        model.addAttribute("user", getUserName());
        return "index";
    }

}
