package org.licence.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by ionut on 09.03.2016.
 */
@Controller
public class HelloController {

    @RequestMapping("/hello/")
    public String getHello(){
        return "hello";
    }

}
