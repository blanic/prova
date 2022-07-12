package it.uniroma3.siw.bookclub.controller;

import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.bookclub.model.Autore;
import it.uniroma3.siw.bookclub.model.Libro;
import it.uniroma3.siw.bookclub.model.Recensione;
import it.uniroma3.siw.bookclub.service.AutoreService;
import it.uniroma3.siw.bookclub.service.LibroService;
import it.uniroma3.siw.bookclub.controller.validator.AutoreValidator;

@Controller
public class LibroController {
	
	@Autowired
	AutoreService autoreService;
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	AutoreValidator validator;

	
	/****************************************************************************
	 * USER
	 ***********************************************************************/

	@GetMapping(value = "/user/libro/{id}")
	public String getLibro(@PathVariable("id") Long id, Model model) {
		Libro libro = this.libroService.findById(id);
		model.addAttribute("libro", libro);
		Autore autore = libro.getAutore();
		model.addAttribute("autore", autore);
		List<Recensione> recensioni = libro.getRecensioni();
		model.addAttribute("recensioni", recensioni);
		return "user/libro/libro.html";
	}

	/****************************************************************************
	 * ADMIN
	 ***********************************************************************/

	@GetMapping(value = "/admin/libro/{id}")
	public String getAdminAutore(@PathVariable("id") Long id, Model model) {
		Libro libro = this.libroService.findById(id);
		model.addAttribute("libro", libro);
		Autore autore = libro.getAutore();
		model.addAttribute("autore", autore);
		List<Recensione> recensioni = libro.getRecensioni();
		model.addAttribute("recensioni", recensioni);
		return "admin/libro/libro.html";
	}

	@GetMapping(value = "/admin/libri")
	public String getAdminAutori(Model model) {
		List<Libro> libri = this.libroService.findAllOrdered();
		model.addAttribute("libri", libri);
		return "admin/libro/libri.html";
	}

	@PostMapping(value = "/admin/libroForm")
	public String getNewLibro(Model model) {
		model.addAttribute("libro", new Libro());
		return "admin/libro/libroForm.html";
	}

	@PostMapping(value = "/admin/libro")
	public String addLibro(@Valid @ModelAttribute("libro") Libro libro,
			BindingResult bindingResult, Model model) {
		validator.validate(libro, bindingResult);
		if (!bindingResult.hasErrors()) {
			libroService.save(libro);
			model.addAttribute("libro", libro);
			Autore autore = libro.getAutore();
			model.addAttribute("autore", autore);
			List<Recensione> recensioni = libro.getRecensioni();
			model.addAttribute("recensioni", recensioni);
			return "admin/libro/libro.html";
		}
		return "admin/libro/libroForm.html";
	}

	@PostMapping(value = "/admin/toDeleteLibro/{id}")
	public String toDeleteLibro(@PathVariable("id") Long id, Model model) {
		Libro libro = this.libroService.findById(id);
		model.addAttribute("libro", libro);
		return "admin/libro/toDeleteLibro.html";
	}

	@PostMapping(value = "/admin/deleteLibro/{id}")
	public String deleteAutore(@PathVariable("id") Long id, Model model) {
		Libro libro = this.libroService.findById(id);
		
		Autore autore = libro.getAutore();
		autore.getLibri().remove(libro);
		this.autoreService.save(autore);
		this.libroService.deleteById(id);
		
		List<Libro> libri = this.libroService.findAllOrdered();
		model.addAttribute("libri", libri);
		return "admin/libro/libri.html";
	}
	
	@GetMapping("/admin/toSetAutore/{idLibro}")
	public String toSetAutore(@PathVariable("idLibro") Long idLibro, Model model) {
		Libro libro = this.libroService.findById(idLibro);
		List<Autore> autori = this.autoreService.findAllOrdered();
		model.addAttribute("libro", libro);
		model.addAttribute("autori", autori);
		return "admin/libro/toSetAutore.html";
	}
	
	@PostMapping(value = "/admin/setAutore/{idLibro}/{idAutore}")
	public String setAutore(@PathVariable("idLibro") Long idLibro,
			                @PathVariable("idAutore") Long idAutore, Model model) {
		Libro libro = this.libroService.findById(idLibro);
		Autore autore = this.autoreService.findById(idAutore);
		autore.getLibri().add(libro);
		libro.setAutore(autore);
		this.autoreService.save(autore);
		this.libroService.save(libro);
		model.addAttribute("libro", libro);
		model.addAttribute("autore", autore);
		List<Recensione> recensioni = libro.getRecensioni();
		model.addAttribute("recensioni", recensioni);
		return "admin/libro/libro.html";
	}
}
