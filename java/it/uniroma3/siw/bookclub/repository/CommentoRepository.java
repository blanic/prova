package it.uniroma3.siw.bookclub.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.bookclub.model.Commento;
import it.uniroma3.siw.bookclub.model.Recensione;

public interface CommentoRepository extends CrudRepository<Commento, Long> {

	public List<Commento> findAll();

}
