package org.licence.repository;

import org.licence.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by ionut on 22.03.2016.
 */
public interface UserRoleRepository extends JpaRepository<UserRole, Long> {

}
