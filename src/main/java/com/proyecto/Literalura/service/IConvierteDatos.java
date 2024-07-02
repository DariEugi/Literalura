package com.proyecto.Literalura.service;

public interface IConvierteDatos {

    <T> T obtenerDatos(String json, Class<T> clase);     //tipo de datos genericos

}
