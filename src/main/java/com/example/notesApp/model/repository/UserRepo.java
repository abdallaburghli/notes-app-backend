package com.example.notesApp.model.repository;

import com.example.notesApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepo extends JpaRepository<User, UUID> {

    Optional<User> findByEmailAndActiveIsTrue(String email);
}
