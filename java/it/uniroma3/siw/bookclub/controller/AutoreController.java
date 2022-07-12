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
import it.uniroma3.siw.bookclub.service.AutoreService;
import it.uniroma3.siw.bookclub.service.LibroService;
import it.uniroma3.siw.bookclub.controller.validator.AutoreValidator;

@Controller
public class AutoreController {
	
	@Autowired
	AutoreService autoreService;
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	AutoreValidator validator;

	
	/****************************************************************************
	 * USER
	 ***********************************************************************/

	@GetMapping(value = "/user/autore/{id}")
	public String getAutore(@PathVariable("id") Long id, Model model) {
		Autore autore = this.autoreService.findById(id);
		model.addAttribute("autore", autore);
		return "user/autore/autore.html";
	}

	/****************************************************************************
	 * ADMIN
	 ***********************************************************************/

	
	//implementare aggiungi un libro?
	
	@GetMapping(value = "/admin/autore/{id}")
	public String getAdminAutore(@PathVariable("id") Long id, Model model) {
		Autore autore = this.autoreService.findById(id);
		model.addAttribute("autore", autore);
		return "admin/autore/autore.html";
	}

	@GetMapping(value = "/admin/autori")
	public String getAdminAllAutore(Model model) {
		List<Autore> autori = this.autoreService.findAllOrdered();
		model.addAttribute("autori", autori);
		return "admin/autore/autori.html";
	}

	@GetMapping(value = "/admin/autoreForm")
	public String getNewAutore(Model model) {
		model.addAttribute("autore", new Autore());
		return "admin/autore/autoreForm.html";
	}

	@PostMapping(value = "/admin/creaAutore")
	public String addAutore(@Valid @ModelAttribute("autore") Autore autore,
			BindingResult bindingResult, Model model) {
		validator.validate(autore, bindingResult);
		if (!bindingResult.hasErrors()) {
			autoreService.save(autore);
			model.addAttribute("autore", autore);
			return "admin/autore/autore.html";
		}
		return "admin/autore/autoreForm.html";
	}

	@GetMapping(value = "/admin/toDeleteAutore/{id}")
	public String toDeleteAutore(@PathVariable("id") Long id, Model model) {
		Autore autore = this.autoreService.findById(id);
		model.addAttribute("autore", autore);
		return "admin/ingrediente/toDeleteAutore.html";
	}

	@PostMapping(value = "/admin/deleteAutore/{id}")
	public String deleteAutore(@PathVariable("id") Long id, Model model) {
		Autore autore = this.autoreService.findById(id);
		List<Libro> libri = this.libroService.findAll();
		for (Libro libro : libri) {
			if (libro.getAutore().equals(autore)) {
				libro.setAutore(null);
				this.libroService.save(libro);
			}
		}
		autoreService.deleteById(id);
		List<Autore> autori = this.autoreService.findAllOrdered();
		model.addAttribute("autori", autori);
		return "admin/autori/autori.html";
	}

}
