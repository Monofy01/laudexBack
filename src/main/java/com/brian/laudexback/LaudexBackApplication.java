package com.brian.laudexback;

import com.brian.laudexback.model.Role;
import com.brian.laudexback.model.User;
import com.brian.laudexback.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

@SpringBootApplication
@EnableSwagger2
public class LaudexBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(LaudexBackApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    CommandLineRunner run(UserService userService) { //init users before project starts
        return args -> {
            userService.addRole(new Role(null, "ROLE_ROOT")); // ALL PERMISIONS
            userService.addRole(new Role(null, "ROLE_ADMIN")); // PERMISIONS FOR CREATE STUDENTS
            userService.addRole(new Role(null, "ROLE_STUDENT")); // PERMISIONS FOR CREATE STUDENTS

            userService.addUser(new User(null, "rootUserName", "root", "secreto", new ArrayList<>()));
            userService.addUser(new User(null, "admin", "admin", "testing", new ArrayList<>()));

            userService.addRoleToUser("root", "ROLE_ROOT");
            userService.addRoleToUser("admin", "ROLE_ADMIN");
        };
    }

}
