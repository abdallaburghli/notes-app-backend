package com.example.notesApp.model.repository;

import com.example.notesApp.model.Note;
import com.example.notesApp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface NoteRepo extends JpaRepository<Note, UUID> {

    List<Note> findAllByUser(User user);
}
