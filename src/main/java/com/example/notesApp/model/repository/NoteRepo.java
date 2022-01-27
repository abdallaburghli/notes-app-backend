package com.example.notesApp.model.repository;

import com.example.notesApp.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface NoteRepo extends JpaRepository<Note, UUID> {

}
