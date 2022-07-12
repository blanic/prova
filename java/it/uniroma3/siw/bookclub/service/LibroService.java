package it.uniroma3.siw.bookclub.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.bookclub.model.Commento;
import it.uniroma3.siw.bookclub.model.Libro;
import it.uniroma3.siw.bookclub.model.comparator.ComparatoreDiLibriPerTitoloEAutore;
import it.uniroma3.siw.bookclub.model.comparator.comparatoreDiCommentiPerData;
import it.uniroma3.siw.bookclub.repository.LibroRepository;

@Service
public class LibroService {
	
	@Autowired
	LibroRepository libroRepository;

	public List<Libro> findAll() {
		this.libroRepository.findAll();
		return null;
	}

	public void save(Libro libro) {
		this.libroRepository.save(libro);
		
	}

	public Libro findById(Long id) {
		return this.libroRepository.findById(id).get();
	}

	public List<Libro> findAllOrdered() {
		
		List<Libro> libri = new ArrayList<Libro>();
		for (Libro libro : this.libroRepository.findAll()) {
				libri.add(libro);
			}
		libri.sort(new ComparatoreDiLibriPerTitoloEAutore());

		return libri;
	}

	public void deleteById(Long id) {
		this.libroRepository.deleteById(id);
		
	}

}
