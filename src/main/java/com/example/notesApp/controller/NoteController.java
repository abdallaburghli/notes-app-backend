package com.example.notesApp.controller;

import com.example.notesApp.api.Response;
import com.example.notesApp.pojo.NoteModel;
import com.example.notesApp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/api/v1/notes")
    public Response<NoteModel> addNote(@Valid @RequestBody NoteModel request) {
        NoteModel res = noteService.addNote(request);
        return new Response<>(res);
    }

    @GetMapping("/api/v1/notes")
    public Response<List<NoteModel>> getNotesList() {
        List<NoteModel> res = noteService.getNotes();
        return new Response<>(res);
    }
}