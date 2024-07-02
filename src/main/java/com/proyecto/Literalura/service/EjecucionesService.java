package com.proyecto.Literalura.service;

import com.proyecto.Literalura.model.*;
import com.proyecto.Literalura.repository.AutorRepository;
import com.proyecto.Literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;


@Service
public class EjecucionesService {
    private static final Scanner teclado = new Scanner(System.in);
    private static final ConsumoAPI consumoAPI = new ConsumoAPI();
    private static final String URl_Base = "https://gutendex.com/books/";
    private static final ConvierteDatos conversor = new ConvierteDatos();

    @Autowired
    private LibroRepository repositoryLibro;
    @Autowired
    private AutorRepository repositoryAutor;

    public  void ejecucionUno() {
        System.out.println("Ingrese el nombre del libro que desea buscar");
        var tituloLibro = teclado.nextLine();

        var json = consumoAPI.obtenerDatos(URl_Base + "?search=" + tituloLibro.replace(" ","%20"));
        var datos = conversor.obtenerDatos(json, Resultados.class);
        Optional<LibroDatos> libroBuscado = datos.muestras().stream()
                .filter(l -> l.titulo().toUpperCase().contains(tituloLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            LibroDatos libroDatos = libroBuscado.get();

            Optional<Libro> libroExistente = repositoryLibro.findByTituloIgnoreCase(libroDatos.titulo());
            if (libroExistente.isPresent()) {
                System.out.println("El libro ya ha sido registrado");
            } else {
                System.out.println(libroBuscado.get());

                for (AutorDatos autorDatos : libroDatos.autor()) {
                    Autor autor = new Autor(autorDatos);
                    repositoryAutor.save(autor);
                    Libro libro = new Libro(libroDatos, autor);
                    repositoryLibro.save(libro);
                }
            }
        } else {
            System.out.println("Libro no encontrado");
        }
    }
    @Transactional
    public void ejecucionDos() {
        List<Libro> librosRegistrados = repositoryLibro.findAll();
        if (librosRegistrados.isEmpty()) {
            System.out.println("No hay libros registrados.");
        } else {
            System.out.println("Libros registrados:");
            for (Libro libro : librosRegistrados) {
                System.out.println(libro);
            }
        }
    }
    public void ejecucionTres(){

        List<Autor> autoresRegistrados = repositoryAutor.findAll();
        if (autoresRegistrados.isEmpty()) {
            System.out.println("No hay autores registrados.");
        } else {
            System.out.println("Autores registrados:");
            for (Autor autor : autoresRegistrados) {
                System.out.println("Autor: " + autor.getNombre());
                System.out.println("Fecha de nacimiento: " + (autor.getNacimiento() != null ? autor.getNacimiento() : "Desconocido"));
                System.out.println("Fecha de fallecimiento: " + (autor.getFallecimiento() != null ? autor.getFallecimiento() : "Desconocido"));
                System.out.println("-----------------------");
            }
        }
    }
    public void ejecucionCuatro() {
        System.out.println("Ingresa un año para conocer los autores(as) vivos en el momento");
        String fecha = teclado.nextLine();

        try {
            int year = Integer.parseInt(fecha);
            if (year < 0) {
                System.out.println("Por favor, ingresa un año válido.");
                return;
            }

        List<Autor> autoresVivos = repositoryAutor.findAutoresVivosEnAno(year);

        if (autoresVivos.isEmpty()) {
            System.out.println("No se encontraron autores vivos en el año " + year);
        } else {
            System.out.println("Autores vivos en el año " + year + ":");
            autoresVivos.forEach(System.out::println);
        }
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. Por favor, ingresa un año numérico.");
        }
    }

    @Transactional
    public void ejecucionCinco(){
        System.out.println("""
                Ingrese el idioma para buscar los libros:
                en - para buscar libros en ingles
                es - para buscar libros en español
                fr - para buscar libros en francés
                """);
        var idioma = teclado.nextLine();

        List<Libro> libroPorIdioma = repositoryLibro.findByIdiomasContainsIgnoreCase(idioma);

        if (libroPorIdioma.isEmpty()){
            System.out.println("No hay libros registrados en ese idioma");
        }else {
            for (Libro libro : libroPorIdioma) {
                System.out.println(libro);
            }
        }

    }
}

