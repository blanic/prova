package it.uniroma3.siw.bookclub.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository; 

import it.uniroma3.siw.bookclub.model.Incontro;

public interface IncontroRepository extends CrudRepository<Incontro, Long> {

	public Incontro findByNome(String nome);
	
	//public List<Incontro> findTop2ByDataPresentOrAfterOrderByData(LocalDate data);


}
