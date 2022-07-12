package it.uniroma3.siw.bookclub.controller.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import it.uniroma3.siw.bookclub.model.Commento;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.service.CredentialsService;

/**
 * Validator for Commento
 */
@Component
public class CommentoValidator implements Validator {

    final Integer MAX_COMMENTO_LENGTH = 300;

    @Override
    public void validate(Object o, Errors errors) {
        Commento commento = (Commento) o;
        String testo = commento.getTesto();

        if (testo.isEmpty())
            errors.rejectValue("commento", "required");
        else if (testo.length() > MAX_COMMENTO_LENGTH)
            errors.rejectValue("commento", "size");
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}