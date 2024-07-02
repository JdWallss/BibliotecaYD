package com.Challenges.BibliotecaYd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosTotales(
        @JsonAlias("results") List<DatosLibro> resultados){
}
