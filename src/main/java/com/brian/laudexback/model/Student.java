package com.brian.laudexback.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Student implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String lastnameF;

    @Column(nullable = false)
    private String lastnameM;

    @Column(nullable = false)
    private Date birthday;

    @Column(nullable = false)
    private String gender;

    @Column(nullable = false)
    private String degreeStudies;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Long phone;
}
