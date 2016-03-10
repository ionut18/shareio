package org.licence.entity;

import javax.persistence.*;

/**
 * Created by ionut on 10.03.2016.
 */
@Entity
public class AppUser {

    @Id
    @Column(name = "id_app_user")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APP_USER_GEN")
    @SequenceGenerator(name = "SEQ_APP_USER_GEN", sequenceName = "SEQ_APP_USER", allocationSize = 1, initialValue = 1)
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private boolean enabled;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
}
