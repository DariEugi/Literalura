package com.proyecto.Literalura.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)

public record Resultados(
        @JsonProperty ("results")List<LibroDatos> muestras
        ) {
}
