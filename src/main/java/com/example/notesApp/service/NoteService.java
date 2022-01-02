package com.example.notesApp.service;

import com.example.notesApp.pojo.NoteModel;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface NoteService {

    NoteModel addNote(NoteModel request);

    List<NoteModel> getNotes();

    NoteModel getNote(UUID noteId);

    NoteModel updateNote(UUID noteId, NoteModel request);

    NoteModel deleteNote(UUID noteID);
}
