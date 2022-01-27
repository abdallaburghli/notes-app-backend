package com.example.notesApp;

import com.example.notesApp.service.AuthenticationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;

@SpringBootApplication
public class NotesAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(NotesAppApplication.class, args);
	}

}
