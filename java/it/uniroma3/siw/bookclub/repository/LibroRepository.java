package it.uniroma3.siw.bookclub.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.bookclub.model.Libro;

public interface LibroRepository extends CrudRepository<Libro, Long> {
	
}
