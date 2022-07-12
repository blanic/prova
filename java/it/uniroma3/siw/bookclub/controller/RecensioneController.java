package it.uniroma3.siw.bookclub.controller;

import java.time.LocalDateTime;
import java.util.List;


import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.bookclub.model.Autore;
import it.uniroma3.siw.bookclub.model.Commento;
import it.uniroma3.siw.bookclub.model.Libro;
import it.uniroma3.siw.bookclub.model.Recensione;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.service.AutoreService;
import it.uniroma3.siw.bookclub.service.LibroService;
import it.uniroma3.siw.bookclub.service.RecensioneService;
import it.uniroma3.siw.bookclub.service.UserService;
import it.uniroma3.siw.bookclub.controller.validator.AutoreValidator;

@Controller
public class RecensioneController {
	
	@Autowired
	AutoreService autoreService;
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	AutoreValidator validator;
	
	@Autowired
	RecensioneService recensioneService;
	
	@Autowired
	UserService userService;

	
	/****************************************************************************
	 * USER
	 ***********************************************************************/

	@GetMapping(value = "/user/recensione/{id}")
	public String getRecensione(@PathVariable("id") Long id, Model model) {
		Recensione recensione = recensioneService.findById(id);
		model.addAttribute("recensione", recensione);
		User autore = (User) recensione.getAutore();
		model.addAttribute("autore", autore);
		Libro libro = recensione.getLibro();
		model.addAttribute("libro", libro);
		List<Commento> commenti = recensione.getCommenti();
		model.addAttribute("commenti", commenti);
		return "user/recensione/recensione.html";
	}
	
	@PostMapping(value = "/user/recensioneForm/{idLibro}")
	public String getNewLibro(@PathVariable("idLibro") Long idLibro, Model model) {
		Libro libro = this.libroService.findById(idLibro);
		model.addAttribute("recensione", new Recensione());
		model.addAttribute("libro", libro);
		return "user/recensione/recensioneForm.html";
	}
	
	@PostMapping(value = "/user/recensione/{idLibro}")
	public String toSetLibroRecensione(@Valid @ModelAttribute("recensione") Recensione recensione,
			@PathVariable("idLibro") Long idLibro,
			BindingResult bindingResult, Model model) {
		validator.validate(recensione, bindingResult);
		if (!bindingResult.hasErrors()) {
			recensione.setDataOrario(LocalDateTime.now());
			Libro libro = this.libroService.findById(idLibro);
			recensione.setLibro(libro);
			libro.getRecensioni().add(recensione);
			User autore = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	autore.getRecensioni().add(recensione);
	    	this.recensioneService.save(recensione);
	    	
			model.addAttribute("recensione", recensione);
			model.addAttribute("autore", autore);
			model.addAttribute("libro", libro);
			List<Commento> commenti = recensione.getCommenti();
			model.addAttribute("commenti", commenti);
			return "user/recensione/recensione.html";
		}
		return "user/libro/libroForm.html";
	}

	/****************************************************************************
	 * ADMIN
	 ***********************************************************************/

	@GetMapping(value = "/admin/recensione/{id}")
	public String getAdminRecensione(@PathVariable("id") Long id, Model model) {
		Recensione recensione = recensioneService.findById(id);
		model.addAttribute("recensione", recensione);
		User autore = (User) recensione.getAutore();
		model.addAttribute("autore", autore);
		Libro libro = recensione.getLibro();
		model.addAttribute("libro", libro);
		List<Commento> commenti = recensione.getCommenti();
		model.addAttribute("commenti", commenti);
		return "admin/recensione/recensione.html";
	}

	@PostMapping(value = "/admin/toDeleteRecensione/{id}")
	public String toDeleteRecensione(@PathVariable("id") Long id, Model model) {
		Recensione recensione = this.recensioneService.findById(id);
		model.addAttribute("recensione", recensione);
		User autore = recensione.getAutore();
		model.addAttribute("autore", autore);
		Libro libro = recensione.getLibro();
		model.addAttribute("libro", libro);
		return "admin/libro/toDeleteRecensione.html";
	}

	@PostMapping(value = "/admin/deleteRecensione/{id}")
	public String deleteRecensione(@PathVariable("id") Long id, Model model) {
		Recensione recensione = this.recensioneService.findById(id);
		Libro libro = recensione.getLibro();
		libro.getRecensioni().remove(recensione);
		User autoreCommento = recensione.getAutore();
		autoreCommento.getRecensioni().remove(recensione);
		this.libroService.save(libro);
		this.userService.saveUser(autoreCommento);
		this.recensioneService.deleteById(id);
	
		model.addAttribute("libro", libro);
		Autore autore = libro.getAutore();
		model.addAttribute("autore", autore);
		List<Recensione> recensioni = libro.getRecensioni();
		model.addAttribute("recensioni", recensioni);
		return "admin/libro/libro.html";
	}
	
}
