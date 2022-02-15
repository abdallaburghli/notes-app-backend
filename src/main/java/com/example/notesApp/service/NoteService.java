package com.example.notesApp.service;

import com.example.notesApp.api.PaginatedResponse;
import com.example.notesApp.api.PaginationRequest;
import com.example.notesApp.config.CurrentUser;
import com.example.notesApp.pojo.NoteModel;
import org.springframework.data.domain.Page;

import java.util.UUID;

public interface NoteService {

    NoteModel addNote(NoteModel request, CurrentUser currentUser);

    PaginatedResponse<NoteModel> getNotes(PaginationRequest request, CurrentUser currentUser);

    NoteModel getNote(UUID noteId, CurrentUser currentUser);

    NoteModel updateNote(UUID noteId, NoteModel request, CurrentUser currentUser);

    void deleteNote(UUID noteId, CurrentUser currentUser);
}
