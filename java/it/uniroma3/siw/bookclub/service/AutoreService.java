package it.uniroma3.siw.bookclub.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.bookclub.model.Autore;
import it.uniroma3.siw.bookclub.model.comparator.ComparatoreDiAutoriPerCognomeENome;
import it.uniroma3.siw.bookclub.repository.AutoreRepository;

@Service
public class AutoreService {
	
	@Autowired
	AutoreRepository autoreRepository;

	public Autore findById(Long id) {
		return this.autoreRepository.findById(id).get();
	}

	public List<Autore> findAllOrdered() {
		List<Autore> autori = new ArrayList<>();
		for(Autore autore : autoreRepository.findAll()) {
			autori.add(autore);
		}
	    autori.sort(new ComparatoreDiAutoriPerCognomeENome());
	    
	    return autori;
	}

	public void save(@Valid Autore autore) {
		this.autoreRepository.save(autore);
		
	}

	public void deleteById(Long id) {
		this.autoreRepository.deleteById(id);
	}

}

