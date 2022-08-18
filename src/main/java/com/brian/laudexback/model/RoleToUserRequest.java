package com.brian.laudexback.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Setter
@Getter
public class RoleToUserRequest {
    private String userName;
    private String roleName;
}