package it.uniroma3.siw.bookclub.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import it.uniroma3.siw.bookclub.model.Autore;

public interface AutoreRepository extends CrudRepository<Autore, Long> {

	public List<Autore> findAll();
}
