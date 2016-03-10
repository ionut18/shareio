package org.licence.repository;

import org.licence.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ionut on 10.03.2016.
 */
public interface AppUserRepository extends JpaRepository<AppUser, Long>{

    AppUser findByUsernameAndPassword(String username, String password);

}
