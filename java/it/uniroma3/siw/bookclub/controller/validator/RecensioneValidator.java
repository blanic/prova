package it.uniroma3.siw.bookclub.controller.validator;

import java.time.Year;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.bookclub.model.Credentials;
import it.uniroma3.siw.bookclub.model.Libro;
import it.uniroma3.siw.bookclub.model.Recensione;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.service.CredentialsService;
import it.uniroma3.siw.bookclub.service.LibroService;
import it.uniroma3.siw.bookclub.service.RecensioneService;

/**
 * Validator for Recensione
 */
@Component
public class RecensioneValidator implements Validator {

	@Autowired
	private RecensioneService recensioneService;

	final Integer MAX_TITOLO_LENGTH = 30;
	final Integer MIN_TITOLO_LENGTH = 3;
	final Integer MAX_TESTO_LENGTH = 3000;
	final Integer MIN_TESTO_LENGTH = 50;

	@Override
	public void validate(Object o, Errors errors) {
		Recensione recensione = (Recensione) o;
		String titolo = recensione.getTitolo();
		String testo = recensione.getTesto();
		User autore = recensione.getAutore();
		Libro libro = recensione.getLibro();

		if (titolo.isEmpty())
			errors.rejectValue("titolo", "required");
		else if (titolo.length() < MIN_TITOLO_LENGTH || titolo.length() > MAX_TITOLO_LENGTH)
			errors.rejectValue("titolo", "size");

		if (testo.isEmpty())
			errors.rejectValue("testo", "required");
		else if (testo.length() < MIN_TESTO_LENGTH || testo.length() > MAX_TESTO_LENGTH)
			errors.rejectValue("testo", "size");
		
		if(this.recensioneService.findByAutoreAndLibro(autore, libro) != null) {
			errors.rejectValue("recensione", "duplicate");
		}


	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}