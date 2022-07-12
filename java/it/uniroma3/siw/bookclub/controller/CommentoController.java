package it.uniroma3.siw.bookclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.bookclub.controller.validator.AutoreValidator;
import it.uniroma3.siw.bookclub.model.Commento;
import it.uniroma3.siw.bookclub.model.Recensione;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.service.AutoreService;
import it.uniroma3.siw.bookclub.service.CommentoService;
import it.uniroma3.siw.bookclub.service.LibroService;
import it.uniroma3.siw.bookclub.service.RecensioneService;
import it.uniroma3.siw.bookclub.service.UserService;

@Controller
public class CommentoController {
	
	@Autowired
	AutoreService autoreService;
	
	@Autowired
	LibroService libroService;
	
	@Autowired
	AutoreValidator validator;

	@Autowired
	CommentoService commentoService;
	
	@Autowired 
	UserService userService;
	
	@Autowired 
	RecensioneService recensioneService;
	
	/****************************************************************************
	 * USER
	 ***********************************************************************/

//	@GetMapping(value = "/user/commento/{id}")
//	public String getCommento(@PathVariable("id") Long id, Model model) {
//		Commento commento = this.commentoService.findById(id);
//		model.addAttribute("commento", commento);
//		return "user/autore/autore.html";
//	}
	
	@PostMapping(value = "user/commento/commentoForm/{idRecensione}")
	public String getNewCommento(@PathVariable("id") Long id, Model model) {
		Recensione recensione = this.recensioneService.findById(id);
		model.addAttribute("recensione", recensione);
		model.addAttribute("commento", new Commento());
		return "user/commento/commentoForm.html";
	}

	@PostMapping(value = "/user/commento/{idRecensione}")
	public String addCommento(@Valid @ModelAttribute("commento") Commento commento, 
			                  @PathVariable("idRecensione") Long idRecensione,
			                  BindingResult bindingResult, Model model) {
		validator.validate(commento, bindingResult);
		if (!bindingResult.hasErrors()) {
			Recensione recensione = this.recensioneService.findById(idRecensione);
			User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
	    	recensione.getCommenti().add(commento);
			commento.setAutore(user);
			commento.setRecensione(recensione);
			commentoService.save(commento);
			model.addAttribute("recensione", recensione);
			return "user/recensione/recensione.html";
		}
		return "user/commento/failed.html";
	}

	/****************************************************************************
	 * ADMIN
	 ***********************************************************************/

//	@GetMapping(value = "/admin/commento/{id}")
//	public String getAdminCommentoUtente(@PathVariable("id") Long id, Model model) {
//		Commento commento = this.commentoService.findById(id);
//		model.addAttribute("commento", commento);
//		return "admin/commento/commento.html";
//	}

//	@GetMapping(value = "/admin/commentiRecensione/{id}")
//	public String getAdminCommentiRecensione(@PathVariable("id") Long id, Model model) {
//		Recensione recensione = this.recensioneService.findById(id);
//		List<Commento> commenti = this.commentoService.findAllByRecensione(recensione);
//		model.addAttribute("commenti", commenti);
//		return "admin/commento/commenti.html";
//	}
	
//	@GetMapping(value = "/admin/commentiUtente/{id}")
//	public String getAdminAllAutore(@PathVariable("id") Long id, Model model) {
//		User autore = this.userService.findById(id);
//		List<Commento> commenti = this.commentoService.findAllByAutore(autore);
//		model.addAttribute("commenti", commenti);
//		return "admin/commento/commenti.html";
//	}

	@PostMapping(value = "/admin/toDeleteCommento/{id}")
	public String toDeleteCommento(@PathVariable("id") Long id, Model model) {
		Commento commento = this.commentoService.findById(id);
		model.addAttribute("commento", commento);
		return "admin/commento/toDeleteCommento.html";
	}

	@PostMapping(value = "/admin/deleteCommento/{id}")
	public String deleteCommento(@PathVariable("id") Long id, Model model) {
		Commento commento = this.commentoService.findById(id);
		Recensione recensione = commento.getRecensione();
		recensione.getCommenti().remove(commento);
		User autore = commento.getAutore();
		autore.getCommenti().remove(commento);
		this.commentoService.deleteById(id);
		model.addAttribute("recensione", recensione);
		return "admin/recensione/recensione.html";
	}

}
