package com.proyecto.Literalura.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class ConsumoAPI {

    public String obtenerDatos(String url) {
        var response = new StringBuilder();

        try {
            var connection = new URL(url).openConnection();
            connection.connect();

            try (var reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String inputLine;
                while ((inputLine = reader.readLine()) != null) {
                    response.append(inputLine);
                }
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return response.toString();
    }
}



