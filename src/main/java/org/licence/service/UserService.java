package org.licence.service;

import org.licence.entity.AppUser;
import org.licence.entity.UserRole;
import org.licence.repository.AppUserRepository;
import org.licence.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

/**
 * Created by ionut on 10.03.2016.
 */
@Service
public class UserService {

    @Autowired
    AppUserRepository appUserRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    public AppUser getUser(String username, String password) {
        AppUser appUser = appUserRepository.findByUsernameAndPassword(username, password);
        if(appUser != null) {
            return appUser;
        }
        else {
            return null;
        }
    }

    @Transactional
    public Boolean saveUser(AppUser appUser) {
        try {
            appUser.setEnabled(true);
            appUserRepository.save(appUser);
            UserRole userRole = new UserRole();
            userRole.setRole("ROLE_CLIENT");
            userRole.setIdAppUser(appUser.getIdAppUser());
            userRoleRepository.save(userRole);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
