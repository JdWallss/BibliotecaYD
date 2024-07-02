package com.Challenges.BibliotecaYd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibro(
        @JsonAlias("title") String titulo,
        @JsonAlias("authors") List<DatosAutor> autores,
        @JsonAlias("languages") List<String> lenguajes,
        @JsonAlias("download_count") int numeroDescargas
) {

    // Método para obtener el primer idioma de la lista

    public String primerLenguaje() {
        return lenguajes != null && !lenguajes.isEmpty() ? lenguajes.get(0) : null;
    }
}