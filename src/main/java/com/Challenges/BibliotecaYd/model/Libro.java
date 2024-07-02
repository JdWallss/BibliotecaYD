package com.Challenges.BibliotecaYd.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "libro_autor",
            joinColumns = @JoinColumn(name = "libro_id"),
            inverseJoinColumns = @JoinColumn(name = "autor_id")
    )

    private List<Autor> autores ;
    private String lenguajes;
    private int numeroDescargas;

    public Libro() {
    }

    //Constructor que relaciona los datos de DatosLibro con los atributos de Libro

    public Libro(DatosLibro datosLibro, List<Autor> autores) {
        this.titulo = datosLibro.titulo();
        this.autores = autores;
        this.lenguajes = datosLibro.primerLenguaje();
        this.numeroDescargas = datosLibro.numeroDescargas();
    }

    //Metodo ToString

    @Override
    public String toString() {
        return ", titulo='" + titulo + '\'' +
                ", lenguajes='" + lenguajes + '\'' +
                ", numeroDescargas=" + numeroDescargas +
                ", autores=" + autores.stream().map(Autor::getNombre).collect(Collectors.joining(", "));
    }

    //Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutores() {
        return autores;
    }

    public void setAutores(List<Autor> autores) {
        this.autores = autores;
    }

    public String getLenguajes() {
        return lenguajes;
    }

    public void setLenguajes(String lenguajes) {
        this.lenguajes = lenguajes;
    }

    public int getNumeroDescargas() {
        return numeroDescargas;
    }

    public void setNumeroDescargas(int numeroDescargas) {
        this.numeroDescargas = numeroDescargas;
    }

}

