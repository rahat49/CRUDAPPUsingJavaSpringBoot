package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

//Data Annotation use for reduce boilerplate code.
//such as getters, setters, constructors, toString, equals and hashcode methods
@Data
@Entity
@Table(name = "employees")
public class Employee {
    //For primary key
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long Id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private  String lastName;

    @Column(name = "email", nullable = false)
    private  String email;

}
