package com.example.notesApp.service.impl;

import com.example.notesApp.config.CurrentUser;
import com.example.notesApp.error.CustomException;
import com.example.notesApp.error.Errors;
import com.example.notesApp.mapper.NoteMapper;
import com.example.notesApp.model.Note;
import com.example.notesApp.pojo.NoteModel;
import com.example.notesApp.model.repository.NoteRepo;
import com.example.notesApp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.mapstruct.factory.Mappers;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class NoteServiceImpl implements NoteService {

    private final NoteRepo noteRepo;
    private final NoteMapper noteMapper = Mappers.getMapper(NoteMapper.class);

    @Override
    public NoteModel addNote(NoteModel request, CurrentUser currentUser) {
        Note note = noteMapper.convert(request);
        note.setUser(currentUser.getUser());
        noteRepo.save(note);
        return noteMapper.convert(note);
    }

    @Override
    public List<NoteModel> getNotes(CurrentUser currentUser) {
        List<Note> notes = noteRepo.findAllByUser(currentUser.getUser());
        return notes.stream()
                .map(noteMapper::convert)
                .collect(toList());
    }

    @Override
    public NoteModel getNote(UUID noteId, CurrentUser currentUser) {
        Note note = noteRepo.findByUserAndId(currentUser.getUser(), noteId)
                .orElseThrow(() -> new CustomException(Errors.NOTE_DOES_NOT_EXIST));

        return noteMapper.convert(note);
    }

    @Override
    public NoteModel updateNote(UUID noteId, NoteModel request, CurrentUser currentUser) {
        Note note = noteRepo.findByUserAndId(currentUser.getUser(), noteId)
                .orElseThrow(() -> new CustomException(Errors.NOTE_DOES_NOT_EXIST));
        note.setText(request.getText());
        noteRepo.save(note);
        return noteMapper.convert(note);
    }

    @Override
    @Transactional
    public void deleteNote(UUID noteId, CurrentUser currentUser) {
        try {
            noteRepo.deleteByUserAndId(currentUser.getUser(), noteId);
            noteRepo.flush();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            throw new CustomException(Errors.NOTE_DOES_NOT_EXIST, e);
        }
    }
}
