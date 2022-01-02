package com.example.notesApp.service;

import com.example.notesApp.mapper.NoteMapper;
import com.example.notesApp.model.Note;
import com.example.notesApp.pojo.NoteModel;
import com.example.notesApp.repository.NoteRepo;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService{

    private final NoteRepo noteRepo;
    private final NoteMapper noteMapper = Mappers.getMapper(NoteMapper.class);

    @Override
    public NoteModel addNote(NoteModel request) {
        System.out.println(request.getText());
        Note note = noteMapper.convert(request);
        noteRepo.save(note);
        return noteMapper.convert(note);
    }

    @Override
    public List<NoteModel> getNotes() {
        List<Note> notes = noteRepo.findAll();
        return notes.stream()
                .map(noteMapper::convert)
                .collect(toList());
    }

    @Override
    public NoteModel getNote(UUID noteId) {
        return null;
    }

    @Override
    public NoteModel updateNote(UUID noteId, NoteModel request) {
        return null;
    }

    @Override
    public NoteModel deleteNote(UUID noteID) {
        return null;
    }
}
