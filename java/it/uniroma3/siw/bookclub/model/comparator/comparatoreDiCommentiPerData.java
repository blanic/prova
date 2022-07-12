package it.uniroma3.siw.bookclub.model.comparator;

import java.util.Comparator;

import it.uniroma3.siw.bookclub.model.Commento;

public class comparatoreDiCommentiPerData implements Comparator<Commento> {

	@Override
	public int compare(Commento o1, Commento o2) {
		
		return o1.getDataOrario().compareTo(o2.getDataOrario());
	}

}
