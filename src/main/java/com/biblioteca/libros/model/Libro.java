package com.biblioteca.libros.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El título es obligatorio")
    @Size(min = 2, max = 150, message = "El título debe tener entre 2 y 150 caracteres")
    @Column(nullable = false, length = 150)
    private String titulo;

    @NotBlank(message = "El autor es obligatorio")
    @Column(nullable = false, length = 100)
    private String autor;

    @NotBlank(message = "El género es obligatorio")
    @Column(nullable = false, length = 50)
    private String genero;

    @NotNull(message = "El número de páginas es obligatorio")
    @Min(value = 1, message = "El libro debe tener al menos 1 página")
    @Column(nullable = false)
    private Integer paginas;

    @NotBlank(message = "Debes indicar el estado")
    @Column(nullable = false, length = 30)
    private String estado; 

    @NotNull(message = "La calificación es obligatoria")
    @Min(value = 1, message = "La calificación mínima es 1")
    @Max(value = 5, message = "La calificación máxima es 5")
    @Column(nullable = false)
    private Integer calificacion;

    public Libro() {}

    public Libro(String titulo, String autor, String genero, Integer paginas, String estado, Integer calificacion) {
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.paginas = paginas;
        this.estado = estado;
        this.calificacion = calificacion;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }
    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }
    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }
    public Integer getPaginas() { return paginas; }
    public void setPaginas(Integer paginas) { this.paginas = paginas; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public Integer getCalificacion() { return calificacion; }
    public void setCalificacion(Integer calificacion) { this.calificacion = calificacion; }
}