package com.libreria.nueva.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.libreria.nueva.entity.Libro;
import com.libreria.nueva.repository.LibroRepository;

@Controller
public class LibroController {
	
	@Autowired
	private LibroRepository libroRepository;

	@GetMapping({ "/", "" })
	public String verPaginaDeInicio(Model model) {
		List<Libro> libros =libroRepository.findAll();
		model.addAttribute("libros", libros);
		return "index";
	}

	@GetMapping("/nuevo")
	public String mostrarFormularioDeRegistrarLibro(Model model) {
		Libro libro = new Libro();
		model.addAttribute("libro", libro);
		return "nuevo";

	}
	
	@PostMapping ("/nuevo")
	public String guardarLibro(@Validated Libro libro,BindingResult bindingResult,RedirectAttributes redirect,Model model) {
		if(bindingResult.hasErrors()) {
			model.addAttribute("libro",libro);
			return "nuevo";
		}
		
		libroRepository.save(libro);
		redirect.addFlashAttribute("msgExito", "El libro "+ libro.getTitulo() + " se almaceno exitosamente!!");
		return "redirect:/";
	}
    
	@GetMapping("/{id}/editar")
	public String mostrarFormularioDeEditararLibro(@PathVariable Long id, Model model) {
		Libro libro = libroRepository.findById(id).get();
		model.addAttribute("libro", libro);
		
		return "nuevo";

	}
	
	@PostMapping ("/{id}/editar")
	public String actualizarLibro(@PathVariable Long id,@Validated Libro libro,BindingResult bindingResult,RedirectAttributes redirect,Model model) {
		Libro libroDB = libroRepository.findById(id).get();
		
		
		if(bindingResult.hasErrors()) {
			model.addAttribute("libro", libro);
			return "nuevo";
		}
		libroDB.setTitulo(libro.getTitulo());
		libroDB.setAutor(libro.getAutor());
		libroDB.setPrecio(libro.getPrecio());
		libroDB.setFechaLanzamiento(libro.getFechaLanzamiento());
		libroRepository.save(libroDB);
		redirect.addFlashAttribute("msgExito", "El libro " + libroDB.getTitulo() + " se modifico exitosamente!!");
		return "redirect:/";
	}
	
	@PostMapping("/{id}/eliminar") 
	public String EliminarLibro(@PathVariable Long id, RedirectAttributes redirect) {
		Libro libro = libroRepository.findById(id).get();
		libroRepository.delete(libro);
		redirect.addFlashAttribute("msgExito", "El libro " + libro.getTitulo() + " se elimino exitosamente!!");
		return "redirect:/";

	}
}
