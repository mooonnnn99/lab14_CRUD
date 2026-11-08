package com.biblioteca.libros.repository;

import com.biblioteca.libros.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LibroRepository extends JpaRepository<Libro, Long> {
    List<Libro> findByEstadoIgnoreCase(String estado);
}