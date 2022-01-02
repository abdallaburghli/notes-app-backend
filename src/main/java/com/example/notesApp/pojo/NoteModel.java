package com.example.notesApp.pojo;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.UUID;

@Getter
@Setter
public class NoteModel {

    private UUID noteId;

    @NotBlank
    private String text;
}
