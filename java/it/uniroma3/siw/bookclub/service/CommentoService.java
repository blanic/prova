package it.uniroma3.siw.bookclub.service;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import it.uniroma3.siw.bookclub.model.Autore;
import it.uniroma3.siw.bookclub.model.Commento;
import it.uniroma3.siw.bookclub.model.Recensione;
import it.uniroma3.siw.bookclub.model.User;
import it.uniroma3.siw.bookclub.model.comparator.comparatoreDiCommentiPerData;
import it.uniroma3.siw.bookclub.repository.CommentoRepository;

@Service
public class CommentoService {

	@Autowired
	CommentoRepository commentoRepository;

	public Commento findById(Long id) {
		return this.commentoRepository.findById(id).get();
	}

	public List<Commento> findAllByRecensione(Recensione recensione) {

		List<Commento> commenti = new ArrayList<Commento>();
		for (Commento commento : commentoRepository.findAll()) {
			if(commento.getRecensione().equals(recensione)) {
				commenti.add(commento);
			}
		}
		commenti.sort(new comparatoreDiCommentiPerData());

		return commenti;
	}

	public List<Commento> findAllByAutore(User autore) {

		List<Commento> commenti = new ArrayList<Commento>();
		for (Commento commento : commentoRepository.findAll()) {
			if(commento.getAutore().equals(autore)) {
				commenti.add(commento);
			}
		}
		commenti.sort(new comparatoreDiCommentiPerData());

		return commenti;
	}

	public void save(@Valid Commento commento) {
		this.commentoRepository.save(commento);
	}

	public void deleteById(Long id) {
		this.commentoRepository.deleteById(id);
		
	}


}

