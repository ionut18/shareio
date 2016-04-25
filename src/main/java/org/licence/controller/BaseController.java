package org.licence.controller;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * Created by ionut on 02.04.2016.
 */
public class BaseController {

    @ModelAttribute("user")
    protected static String getUserName() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//        auth object is never null (when you are not logged in there is an instance of anonymous user)
        if(auth instanceof AnonymousAuthenticationToken) {
            return null;
        }
        else {
            return auth.getName();
        }
    }

}
