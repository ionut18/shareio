package org.licence.entity;

import javax.persistence.*;

/**
 * Created by ionut on 10.03.2016.
 */
@Entity
public class UserRole {

    @Id
    @Column(name = "id_user_role")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE_GEN")
    @SequenceGenerator(name = "SEQ_USER_ROLE_GEN", sequenceName = "SEQ_USER_ROLE", allocationSize = 1, initialValue = 1)
    private Long id;

    private String role;

    @ManyToOne(targetEntity = AppUser.class)
    @JoinColumn(name = "id_app_user")
    private AppUser user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public AppUser getUser() {
        return user;
    }

    public void setUser(AppUser user) {
        this.user = user;
    }
}
