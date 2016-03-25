package org.licence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by ionut on 10.03.2016.
 */
@Entity
@Getter
@Setter
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APP_USER_GEN")
    @SequenceGenerator(name = "SEQ_APP_USER_GEN", sequenceName = "SEQ_APP_USER", allocationSize = 1, initialValue = 1)
    private Long idAppUser;

    @NotNull
    private String username;

    @NotNull
    private String password;

    @NotNull
    private String firstName;

    @NotNull
    private String lastName;

    @NotNull
    private String email;

    @NotNull
    private String telephone;

    private boolean enabled;
}
