package it.uniroma3.siw.bookclub.service;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.uniroma3.siw.bookclub.model.Incontro;
import it.uniroma3.siw.bookclub.repository.IncontroRepository;

@Service
public class IncontroService {
	
	@Autowired
	IncontroRepository incontroRepository;

	public Incontro getIncontro(String nome) {
		return this.incontroRepository.findByNome(nome);
	}

	public Incontro findById(Long id) {
		return this.incontroRepository.findById(id).get();
	}

	public void save(@Valid Incontro incontro) {
		this.incontroRepository.save(incontro);
		
	}

	public void deleteById(Long id) {
		
		this.incontroRepository.deleteById(id);
	}

}
