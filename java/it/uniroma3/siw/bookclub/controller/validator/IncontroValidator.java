package it.uniroma3.siw.bookclub.controller.validator;

import java.time.LocalDate;
import java.time.LocalTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import it.uniroma3.siw.bookclub.model.Credentials;
import it.uniroma3.siw.bookclub.model.Incontro;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.service.CredentialsService;
import it.uniroma3.siw.bookclub.service.IncontroService;

/**
 * Validator for Incontro
 */
@Component
public class IncontroValidator implements Validator {

    @Autowired
    private IncontroService incontroService;

    final Integer MAX_NOME_LENGTH = 30;
    final Integer MIN_NOME_LENGTH = 3;
    final Integer MAX_DESC_LENGTH = 3000;
    final Integer MIN_DESC_LENGTH = 3;
    final Integer MIN_NUM_POSTI = 5;
    final Integer MAX_NUM_POSTI = 15;

    @Override
    public void validate(Object o, Errors errors) {
        Incontro incontro = (Incontro) o;
        String nome = incontro.getNome();
        String descrizione = incontro.getDescrizione();
        int numeroPosti = incontro.getNumeroPosti();
        LocalDate data = incontro.getData();
  

        if (nome.isEmpty())
            errors.rejectValue("nome", "required");
        else if (nome.length() < MIN_NOME_LENGTH || nome.length() > MAX_NOME_LENGTH)
            errors.rejectValue("nome", "size");
        else if (this.incontroService.getIncontro(nome) != null)
            errors.rejectValue("nome", "duplicate");

        if (descrizione.isEmpty())
            errors.rejectValue("descrizione", "required");
        else if (descrizione.length() < MIN_DESC_LENGTH || descrizione.length() > MAX_DESC_LENGTH)
            errors.rejectValue("descrizione", "size");
        
        if (numeroPosti < MIN_NUM_POSTI || numeroPosti > MAX_NUM_POSTI)
            errors.rejectValue("descrizione", "size");
         
        if(data.isBefore(LocalDate.now())) {
        	errors.rejectValue("ora", "passato");
        }
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.equals(clazz);
    }

}