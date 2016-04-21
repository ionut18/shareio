package org.licence.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by ionut on 02.04.2016.
 */
public class BaseController {

    @ModelAttribute("user")
    public String getDisplayName() {
        return getUserName();
    }

    protected static String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getName();
    }

}
