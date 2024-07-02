package com.Challenges.BibliotecaYd.service;

public interface IConvierteDatos {
    <T> T obtenerDatos (String json, Class<T> clase);
}
