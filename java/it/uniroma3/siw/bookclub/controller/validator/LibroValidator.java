package it.uniroma3.siw.bookclub.controller.validator;

import java.time.Year;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.bookclub.model.Credentials;
import it.uniroma3.siw.bookclub.model.Libro;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.service.CredentialsService;
import it.uniroma3.siw.bookclub.service.LibroService;

/**
 * Validator for Credentials
 */
@Component
public class LibroValidator implements Validator {

	@Autowired
	private LibroService libroService;

	final Integer MAX_TITOLO_LENGTH = 30;
	final Integer MIN_TITOLO_LENGTH = 3;
	final Integer MAX_DESC_LENGTH = 3000;
	final Integer MIN_DESC_LENGTH = 3;

	
	@Override
	public void validate(Object o, Errors errors) {
		Libro libro = (Libro) o;
		String titolo = libro.getTitolo();
		String descrizione = libro.getDescrizione();
		Year anno = libro.getAnno();

		if (titolo.isEmpty())
			errors.rejectValue("titolo", "required");
		else if (titolo.length() < MIN_TITOLO_LENGTH || titolo.length() > MAX_TITOLO_LENGTH)
			errors.rejectValue("titolo", "size");

		if (descrizione.isEmpty())
			errors.rejectValue("descrizione", "required");
		else if (descrizione.length() < MIN_DESC_LENGTH || descrizione.length() > MAX_DESC_LENGTH)
			errors.rejectValue("descrizione", "size");

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}