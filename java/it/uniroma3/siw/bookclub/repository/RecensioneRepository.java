package it.uniroma3.siw.bookclub.repository;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.bookclub.model.Libro;
import it.uniroma3.siw.bookclub.model.Recensione;
import it.uniroma3.siw.bookclub.model.User;

public interface RecensioneRepository extends CrudRepository<Recensione, Long> {
	
	public Recensione findByAutoreAndLibro(User Autore, Libro libro);

}
