package it.uniroma3.siw.bookclub.controller.validator;


import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import it.uniroma3.siw.bookclub.model.Autore;
import it.uniroma3.siw.bookclub.model.User;


/**
 * Validator for Autore
 */
@Component
public class AutoreValidator implements Validator {


	final Integer MAX_NOME_LENGTH = 30;
	final Integer MIN_NOME_LENGTH = 3;
	final Integer MAX_COGN_LENGTH = 30;
	final Integer MIN_COGN_LENGTH = 3;
	final Integer MAX_BIO_LENGTH = 2000;

	@Override
	public void validate(Object o, Errors errors) {
		Autore autore = (Autore) o;
		String nome = autore.getNome();
		String cognome = autore.getCognome();
		String bio = autore.getBiografia();

		if (nome.isEmpty())
			errors.rejectValue("nome", "required");
		else if (nome.length() < MIN_NOME_LENGTH || nome.length() > MAX_NOME_LENGTH)
			errors.rejectValue("nome", "size");

		if (cognome.isEmpty())
			errors.rejectValue("cognome", "required");
		else if (cognome.length() < MIN_COGN_LENGTH || cognome.length() > MAX_COGN_LENGTH)
			errors.rejectValue("cognome", "size");
		
		if (bio.length() > MAX_BIO_LENGTH)
			errors.rejectValue("biografia", "size");

	}

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

}