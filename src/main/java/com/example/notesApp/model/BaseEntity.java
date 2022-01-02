package com.example.notesApp.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.UUID;

@MappedSuperclass
@Getter
@Setter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @CreatedDate
    @Column(name = "created_date", columnDefinition = "timestamp")
    private LocalDateTime createdDate;

    @LastModifiedDate
    @Column(name = "updated_date", columnDefinition = "timestamp")
    private LocalDateTime updatedDate;
}
