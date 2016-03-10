package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.repository.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by ionut on 10.03.2016.
 */
@Service
public class UserService {

    @Autowired
    AppUserRepository appUserRepository;

    public AppUser getUser(String username, String password) {
        AppUser appUser = appUserRepository.findByUsernameAndPassword(username, password);
        if(appUser != null) {
            return appUser;
        }
        else {
            return null;
        }
    }

}
