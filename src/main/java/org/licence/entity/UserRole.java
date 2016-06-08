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
public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_ROLE_GEN")
    @SequenceGenerator(name = "SEQ_USER_ROLE_GEN", sequenceName = "SEQ_USER_ROLE", allocationSize = 1, initialValue = 1)
    private Long idUserRole;

    @NotNull
    private String role;

}
