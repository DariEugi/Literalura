package com.proyecto.Literalura.repository;

import com.proyecto.Literalura.model.Autor;
import com.proyecto.Literalura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    Optional<Libro> findByTituloIgnoreCase(String titulo);

    List<Libro> findByIdiomasContainsIgnoreCase(String idioma);

}
