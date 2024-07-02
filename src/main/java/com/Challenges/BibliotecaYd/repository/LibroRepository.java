package com.Challenges.BibliotecaYd.repository;

import com.Challenges.BibliotecaYd.model.Libro;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LibroRepository extends JpaRepository<Libro,Long> {
    //List<Libro> findByIdioma(Idiomas idioma);
    Libro findByTituloContainingIgnoreCase(String titulo);

    //recuperar los autores junto con los libros en una sola consulta
    @EntityGraph(attributePaths = "autores")
    //todos los libros pero con el entityGraph para que me traiga los autores
    @Query("SELECT l FROM Libro l JOIN FETCH l.autores")
    List<Libro> findAllWithAutores();

    List<Libro> findByLenguajesContains(String language);
}