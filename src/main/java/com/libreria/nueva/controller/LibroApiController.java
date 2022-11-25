package com.libreria.nueva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.libreria.nueva.entity.Libro;
import com.libreria.nueva.repository.LibroRepository;

@RestController
public class LibroApiController {

	@Autowired
	private LibroRepository libroRepository;

	@GetMapping("/libros")
	public List<Libro> getAllLibros() {
		List<Libro> libros = libroRepository.findAll();

		return libros;
	}

	@PostMapping("/libros")
	@ResponseStatus(HttpStatus.CREATED)
	public Libro saveLibro(@RequestBody Libro libro) {

		libroRepository.save(libro);

		return libro;
	}
	
	@GetMapping("libros/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Libro show(@PathVariable Long id) {
		return libroRepository.findById(id).get();
	}
    
	@PutMapping("libros/{id}")
	@ResponseStatus(HttpStatus.OK)
	public Libro update(@RequestBody Libro libro,@PathVariable Long id) {
	Libro libroActual = libroRepository.findById(id).get();
	libroActual.setTitulo(libro.getTitulo());
	libroActual.setAutor(libro.getAutor());
	libroActual.setPrecio(libro.getPrecio());
	libroActual.setFechaLanzamiento(libro.getFechaLanzamiento());
	
	return libroRepository.save(libroActual);
	}
	
	@DeleteMapping("/libros")
	@ResponseStatus(HttpStatus.OK)
	public void delete(@RequestBody Libro libro) {
		libroRepository.delete(libro);
	}
}
