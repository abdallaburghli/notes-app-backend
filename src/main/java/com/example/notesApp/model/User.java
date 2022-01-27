package com.example.notesApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.ZonedDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends BaseEntity {

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @Column(name = "full_name")
    private String fullName;

    @Column(name = "active", columnDefinition = "bool default true")
    private Boolean active;

    @Column(name = "root", columnDefinition = "bool default false")
    private boolean root;

    @Column(name = "last_login", columnDefinition = "timestamptz")
    private ZonedDateTime lastLogin;
}
