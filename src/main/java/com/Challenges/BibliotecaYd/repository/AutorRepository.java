package com.Challenges.BibliotecaYd.repository;

import com.Challenges.BibliotecaYd.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<Autor,Long> {

    Optional<Autor> findByNombreContainingIgnoreCase(String nombre);

    @Query("SELECT DISTINCT a FROM Autor a")
    List<Autor> findAllWithoutDuplicates();

    @Query("SELECT a FROM Autor a WHERE :year >= a.fechaNacimiento AND (:year <= a.fechaFallecimiento OR a.fechaFallecimiento IS NULL)")
    List<Autor> autorPorFecha(int year);
}
