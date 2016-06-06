package org.licence.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import javax.sql.DataSource;

/**
 * Created by ionut on 11.03.2016.
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    DataSource dataSource;

    @Autowired
    public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {

        auth.jdbcAuthentication().dataSource(dataSource)
                .usersByUsernameQuery("select username, password, enabled from app_user where username=?")
                .authoritiesByUsernameQuery("select username, role from user_role r, app_user u where u.id_user_role = r.id_user_role and username=?");
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {

        httpSecurity.authorizeRequests()
                .antMatchers("/car/**", "/ride/add","/message/**").access("hasAnyRole('ROLE_ADMIN','ROLE_MODERATOR','ROLE_CLIENT')")
                .anyRequest().permitAll()
                .and()
                    .formLogin().loginPage("/login")
                    .usernameParameter("username").passwordParameter("password")
                .and()
                    .logout().logoutSuccessUrl("/logout")
                .and()
                    .csrf();
    }

}
