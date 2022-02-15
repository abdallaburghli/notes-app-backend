package com.example.notesApp.controller;

import com.example.notesApp.api.PaginatedResponse;
import com.example.notesApp.api.PaginationRequest;
import com.example.notesApp.api.Response;
import com.example.notesApp.config.CurrentUser;
import com.example.notesApp.pojo.NoteModel;
import com.example.notesApp.security.LoggedIn;
import com.example.notesApp.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;

    @PostMapping("/api/v1/notes")
    @LoggedIn
    public Response<NoteModel> addNote(@Valid @RequestBody NoteModel request, CurrentUser currentUser) {
        NoteModel res = noteService.addNote(request, currentUser);
        return new Response<>(res);
    }

    @PostMapping("/api/v1/notes/user")
    @LoggedIn
    public Response<PaginatedResponse<NoteModel>> getNotesList(@RequestBody PaginationRequest request, CurrentUser currentUser) {
        PaginatedResponse<NoteModel> res = noteService.getNotes(request, currentUser);
        return new Response<>(res);
    }

    @GetMapping("/api/v1/notes/{id}")
    @LoggedIn
    public Response<NoteModel> getNote(@PathVariable UUID id, CurrentUser currentUser) {
        NoteModel res = noteService.getNote(id, currentUser);
        return new Response<>(res);
    }

    @PutMapping("/api/v1/notes/{id}")
    @LoggedIn
    public Response<NoteModel> updateNote(@PathVariable UUID id, @RequestBody NoteModel request, CurrentUser currentUser) {
        NoteModel res = noteService.updateNote(id, request, currentUser);
        return new Response<>(res);
    }

    @GetMapping("/api/v1/notes/del/{id}")
    @LoggedIn
    public Response<NoteModel> deleteNote(@PathVariable UUID id, CurrentUser currentUser) {
        noteService.deleteNote(id, currentUser);
        return new Response<>();
    }
}
