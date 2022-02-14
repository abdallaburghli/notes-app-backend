package com.example.notesApp.service;

import com.example.notesApp.config.CurrentUser;
import com.example.notesApp.pojo.NoteModel;

import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteModel addNote(NoteModel request, CurrentUser currentUser);

    List<NoteModel> getNotes(CurrentUser currentUser);

    NoteModel getNote(UUID noteId, CurrentUser currentUser);

    NoteModel updateNote(UUID noteId, NoteModel request, CurrentUser currentUser);

    void deleteNote(UUID noteId, CurrentUser currentUser);
}
