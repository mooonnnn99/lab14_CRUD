package com.biblioteca.libros.controller;

import com.biblioteca.libros.model.Libro;
import com.biblioteca.libros.service.LibroService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Optional;

@Controller
@RequestMapping("/libros")
public class LibroController {

    private final LibroService servicio;

    public LibroController(LibroService servicio) {
        this.servicio = servicio;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("libros", servicio.obtenerTodos());
        return "libros/listar";
    }

    @GetMapping("/nuevo")
    public String mostrarFormularioNuevo(Model model) {
        model.addAttribute("libro", new Libro());
        model.addAttribute("accion", "Registrar Libro");
        return "libros/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@Valid @ModelAttribute("libro") Libro libro,
                          BindingResult resultado,
                          Model model,
                          RedirectAttributes flash) {
        if (resultado.hasErrors()) {
            model.addAttribute("accion", libro.getId() == null ? "Registrar Libro" : "Editar Libro");
            return "libros/formulario";
        }

        servicio.guardar(libro);
        flash.addFlashAttribute("mensajeExito", "¡Libro guardado exitosamente!");
        return "redirect:/libros";
    }

    @GetMapping("/editar/{id}")
    public String mostrarFormularioEditar(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Optional<Libro> libroOpcional = servicio.obtenerPorId(id);
        if (libroOpcional.isEmpty()) {
            flash.addFlashAttribute("mensajeError", "El libro solicitado no existe.");
            return "redirect:/libros";
        }
        model.addAttribute("libro", libroOpcional.get());
        model.addAttribute("accion", "Editar Libro");
        return "libros/formulario";
    }

    @GetMapping("/detalle/{id}")
    public String mostrarDetalle(@PathVariable("id") Long id, Model model, RedirectAttributes flash) {
        Optional<Libro> libroOpcional = servicio.obtenerPorId(id);
        if (libroOpcional.isEmpty()) {
            flash.addFlashAttribute("mensajeError", "El libro solicitado no existe.");
            return "redirect:/libros";
        }
        model.addAttribute("libro", libroOpcional.get());
        return "libros/detalle";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id, RedirectAttributes flash) {
        try {
            servicio.eliminarPorId(id);
            flash.addFlashAttribute("mensajeExito", "Libro eliminado con éxito.");
        } catch (Exception e) {
            flash.addFlashAttribute("mensajeError", "No se pudo eliminar el registro.");
        }
        return "redirect:/libros";
    }
}