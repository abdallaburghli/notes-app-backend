package com.example.notesApp.mapper;

import com.example.notesApp.model.Note;
import com.example.notesApp.pojo.NoteModel;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface NoteMapper {

    Note convert(NoteModel noteModel);

    @Mapping(target = "noteId", source = "id")
    NoteModel convert(Note note);
}
