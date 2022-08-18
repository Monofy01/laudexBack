package com.brian.laudexback.config;

import com.brian.laudexback.filter.CustomAuthenticationFilter;
import com.brian.laudexback.filter.CustomAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    // this class allows us to specify the configuration of access to published resources
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder; //encoder class


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {




        http.csrf().disable(); // disable for token bearer
        http.sessionManagement().sessionCreationPolicy(STATELESS);

        //  ROLE_ROOT can create new users and new roles in different endpoints
        http.authorizeRequests().antMatchers(GET, "/main/all/**").hasAnyAuthority("ROLE_ROOT");
        http.authorizeRequests().antMatchers(POST, "/main/user/add/**").hasAnyAuthority("ROLE_ROOT");
        http.authorizeRequests().antMatchers(PUT, "/main/role/add/**").hasAnyAuthority("ROLE_ROOT");

         // ROLE_ADMIN can create new students
        http.authorizeRequests().antMatchers(GET, "/laudex/all/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROOT");
        http.authorizeRequests().antMatchers(POST, "/laudex/add/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROOT");
        http.authorizeRequests().antMatchers(PUT, "/laudex/update/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROOT");
        http.authorizeRequests().antMatchers(DELETE, "/laudex/delete/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_ROOT");

        // ROLE_ADMIN can create new students
        http.authorizeRequests().antMatchers(GET, "/laudex/all/**").hasAnyAuthority("ROLE_STUDENT");
        http.authorizeRequests().anyRequest().authenticated();
        http.addFilter(new CustomAuthenticationFilter(authenticationManagerBean()));
        http.addFilterBefore(new CustomAuthorizationFilter(), UsernamePasswordAuthenticationFilter.class);


    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }



}
