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

    @Transactional
    public Boolean saveUser(AppUser appUser) {
        try {
            appUser.setEnabled(true);
            appUserRepository.save(appUser);
            UserRole userRole = new UserRole();
            userRole.setRole("ROLE_CLIENT");
            userRoleRepository.save(userRole);
            return true;
        }
        catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public String getUserRole(String username) {

        AppUser user = appUserRepository.findByUsername(username);

        UserRole userRole = userRoleRepository.findOne(user.getIdUserRole());

        return userRole.getRole();
    }

    public AppUser getUserByUsername(String username) {
        return appUserRepository.findByUsername(username);
    }


    public AppUser getUserById(Long id) {
        return appUserRepository.findOne(id);
    }

}
