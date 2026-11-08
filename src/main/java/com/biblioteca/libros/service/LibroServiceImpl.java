package com.biblioteca.libros.service;

import com.biblioteca.libros.model.Libro;
import com.biblioteca.libros.repository.LibroRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class LibroServiceImpl implements LibroService {

    private final LibroRepository repositorio;

    public LibroServiceImpl(LibroRepository repositorio) {
        this.repositorio = repositorio;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Libro> obtenerTodos() {
        return repositorio.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Libro> obtenerPorId(Long id) {
        return repositorio.findById(id);
    }

    @Override
    @Transactional
    public Libro guardar(Libro libro) {
        return repositorio.save(libro);
    }

    @Override
    @Transactional
    public void eliminarPorId(Long id) {
        repositorio.deleteById(id);
    }
}