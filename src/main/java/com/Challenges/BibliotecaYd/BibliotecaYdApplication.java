package com.Challenges.BibliotecaYd;

import com.Challenges.BibliotecaYd.principal.Main;
import com.Challenges.BibliotecaYd.repository.AutorRepository;
import com.Challenges.BibliotecaYd.repository.LibroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.security.Principal;

@SpringBootApplication
public class BibliotecaYdApplication implements CommandLineRunner {

	@Autowired
	private Main main;

	public static void main(String[] args) {
		SpringApplication.run(BibliotecaYdApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//System.out.println("Hola mundo desde Spring");
		main.muestraElMenu();
	}
}
