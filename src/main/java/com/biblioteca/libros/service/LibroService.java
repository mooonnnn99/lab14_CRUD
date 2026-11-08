package com.biblioteca.libros.service;

import com.biblioteca.libros.model.Libro;
import java.util.List;
import java.util.Optional;

public interface LibroService {
    List<Libro> obtenerTodos();
    Optional<Libro> obtenerPorId(Long id);
    Libro guardar(Libro libro);
    void eliminarPorId(Long id);
}