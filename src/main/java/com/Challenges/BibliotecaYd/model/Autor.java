package com.Challenges.BibliotecaYd.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Entity
@Table(name = "autores")
public class Autor {
    //Generando un id por cada autor
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nombre;
    private Integer fechaNacimiento;
    private Integer fechaFallecimiento;
    //Mapeo para relaccionar los autores y los libros
    @ManyToMany(mappedBy = "autores", fetch = FetchType.EAGER)
    private List<Libro> libros = new ArrayList<>();

    public Autor(){}

    public Autor(DatosAutor datosAutor) {
        this.nombre = datosAutor.nombre();
        this.fechaNacimiento = Integer.valueOf(datosAutor.fechaNacimiento());
        this.fechaFallecimiento = Integer.valueOf(datosAutor.fechaFallecimiento());
    }

    //Metodo ToString

    @Override
    public String toString() {
        return
                ", nombre='" + nombre + '\'' +
                ", fechaNacimiento=" + fechaNacimiento +
                ", fechaFallecimiento=" + fechaFallecimiento +
                ", número de libros=" + (libros != null ? libros.size() : 0);
    }


    //Getters & Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Integer fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Integer getFechaFallecimiento() {
        return fechaFallecimiento;
    }

    public void setFechaFallecimiento(Integer fechaFallecimiento) {
        this.fechaFallecimiento = fechaFallecimiento;
    }

    public List<Libro> getLibros() {
        return libros;
    }

    public void setLibros(List<Libro> libros) {
        this.libros = libros;
    }
}
