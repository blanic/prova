package it.uniroma3.siw.bookclub.model.comparator;

import java.util.Comparator;

import it.uniroma3.siw.bookclub.model.Autore;

public class ComparatoreDiAutoriPerCognomeENome implements Comparator<Autore> {

	@Override
	public int compare(Autore o1, Autore o2) {
		
		int comp = o1.getCognome().compareTo(o2.getCognome());
		
		if(comp == 0) {
			return o1.getNome().compareTo(o2.getNome());
		}
		
		return comp;
	}
	
	

}
