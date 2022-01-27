package com.example.notesApp.mapper;

import com.example.notesApp.model.User;
import com.example.notesApp.pojo.UserModel;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapper {

    UserModel convert(User user);
}
