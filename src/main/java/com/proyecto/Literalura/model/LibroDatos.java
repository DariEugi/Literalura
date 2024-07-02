package com.proyecto.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.stream.Collectors;

@JsonIgnoreProperties(ignoreUnknown = true)
public record LibroDatos(

        @JsonProperty("title") String titulo,

        @JsonProperty("authors") List<AutorDatos> autor,

        @JsonProperty("download_count") Integer numeroDescargas,

        @JsonProperty("languages") List<String> idiomas) {

    @Override
    public String toString() {
        String autoresFormatted = autor.stream()
                .map(AutorDatos::nombre)
                .collect(Collectors.joining(", "));
        String idiomasFormatted = String.join(", ", idiomas);

        return "---------Libro---------\n" +
                "Titulo: " + titulo + "\n" +
                "Autor: " + autoresFormatted + "\n" +
                "Idiomas: " + idiomasFormatted + "\n" +
                "Numero de descargas: " + numeroDescargas + "\n" +
                "-----------------------\n";
    }
}


