package com.brian.laudexback.controller;

import com.brian.laudexback.model.Role;
import com.brian.laudexback.model.RoleToUserRequest;
import com.brian.laudexback.model.Student;
import com.brian.laudexback.model.User;
import com.brian.laudexback.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
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



    @ApiOperation(value = "Buscar todos los USUARIOS", notes = "No se require ingresar nada sobre el body content del request")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se obtiene correctamente", response = User.class, responseContainer = "List")})
    @GetMapping("/all")
    public ResponseEntity<List<User>> getUsers() {
        List<User> userList = userService.getUsers();
        return new ResponseEntity<>(userList, HttpStatus.OK);
    }

    @ApiOperation(value = "Agregar USUARIO", notes = "Se requiere ingresar modelo User para hacer " +
            "insercion correctamente, el usuario al ser creado por primera vez no contiene ningun rol")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se agrega correctamente", response = User.class)})
    @PostMapping("/user/add")
    public ResponseEntity<User> addUser(@RequestBody User user) {
        User newUser = userService.addUser(user);
        return new ResponseEntity<>(newUser, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Agregar ROL", notes = "Se requiere ingresar modelo Role para hacer " +
            "insercion correctamente, el rol al ser creado por primera vez no es asignado a ningun usuario")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se agrega correctamente", response = Role.class)})
    @PostMapping("/role/add")
    public ResponseEntity<Role> addRole(@RequestBody Role role) {
        Role newRole = userService.addRole(role);
        return new ResponseEntity<>(newRole, HttpStatus.CREATED);
    }

    @ApiOperation(value = "Agregar ROL a USUARIO", notes = "Se requiere ingresar modelo RoleToUserRequest para hacer insercion correctamente")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "OK. El recurso se agrega correctamente", response = String.class)})
    @PostMapping("/role/addToUser")
    public ResponseEntity<String> addRoleToUser(@RequestBody RoleToUserRequest request) {
        userService.addRoleToUser(request.getUserName(), request.getRoleName());
        return new ResponseEntity<>("Se ha asignado correctamente el ROL al USUARIO", HttpStatus.CREATED);
    }


}


