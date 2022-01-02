package com.example.notesApp.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "note")
@Getter
@Setter
public class Note extends BaseEntity{

    @Column(name = "text")
    private String text;
}
