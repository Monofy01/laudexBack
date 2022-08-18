package com.brian.laudexback.controller;

import com.brian.laudexback.model.Role;
import com.brian.laudexback.model.RoleToUserRequest;
import com.brian.laudexback.model.Student;
import com.brian.laudexback.model.User;
import com.brian.laudexback.service.UserService;
import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/main")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.getUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @PostMapping("/role/add")
    public ResponseEntity<Role> addUser(@RequestBody Role role) {
        Role newRole = userService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @PostMapping("/role/addToUser")
    public ResponseEntity<?> addRoleToUser(@RequestBody RoleToUserRequest request) {
        userService.addRoleToUser(request.getUserName(), request.getRoleName());
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


}


