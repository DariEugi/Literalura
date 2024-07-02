package com.proyecto.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record AutorDatos(
        @JsonProperty("name")String nombre,

        @JsonProperty("birth_year") Integer nacimiento,

        @JsonProperty("death_year") Integer fallecimiento) {


}
