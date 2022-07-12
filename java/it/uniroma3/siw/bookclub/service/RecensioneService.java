package it.uniroma3.siw.bookclub.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.bookclub.model.*;
import it.uniroma3.siw.bookclub.repository.RecensioneRepository;

@Service
public class RecensioneService {
	
	@Autowired 
	RecensioneRepository recensioneRepository;

	public Recensione findByAutoreAndLibro(User autore, Libro libro) {
		return this.recensioneRepository.findByAutoreAndLibro(autore, libro);
	}

	public Recensione findById(Long id) {
		return this.recensioneRepository.findById(id).get();
	}

	public void save(@Valid Recensione recensione) {
		this.recensioneRepository.save(recensione);
	}

	public void deleteById(Long id) {
		this.recensioneRepository.deleteById(id);
		
	}

}
