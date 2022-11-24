package com.libreria.nueva.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import javax.persistence.Id;

@Entity
@Table(name = "libros")
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank(message = "Debe ingresar titulo")
	private String titulo;
	
	@NotBlank(message = "Debe ingresar autor")
	private String autor;
	
	@NotNull(message = "Debe ingresar el precio")
	private Integer precio;
	
	@DateTimeFormat(iso = ISO.DATE)
	@Past
	@NotNull (message="Debe ingresar la fecha de lanzamiento")
	private LocalDate fechaLanzamiento;

	public Libro() {

	}

	public Libro(Long id, String titulo, String autor, Integer precio, LocalDate fechaLanzamiento) {
		super();
		this.id = id;
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Libro(String titulo, String autor, Integer precio, LocalDate fechaLanzamiento) {
		super();
		this.titulo = titulo;
		this.autor = autor;
		this.precio = precio;
		this.fechaLanzamiento = fechaLanzamiento;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public Integer getPrecio() {
		return precio;
	}

	public void setPrecio(Integer precio) {
		this.precio = precio;
	}

	public LocalDate getFechaLanzamiento() {
		return fechaLanzamiento;
	}

	public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
		this.fechaLanzamiento = fechaLanzamiento;
	}

	@Override
	public String toString() {
		return "Libro [id= " + id + ", titulo =" + titulo + ", autor =" + autor + ", precio =" + precio
				+ ", fechaLanzamiento =" + fechaLanzamiento + "]";
	}

}
