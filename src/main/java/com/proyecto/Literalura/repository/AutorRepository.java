package com.proyecto.Literalura.repository;

import com.proyecto.Literalura.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    @Query("SELECT a FROM Autor a WHERE a.nacimiento <= :year AND (a.fallecimiento IS NULL OR a.fallecimiento >= :year)")
    List<Autor> findAutoresVivosEnAno(@Param("year") int year);
}
