package it.uniroma3.siw.bookclub.model.comparator;

import java.util.Comparator;

import it.uniroma3.siw.bookclub.model.Autore;
import it.uniroma3.siw.bookclub.model.Libro;

public class ComparatoreDiLibriPerTitoloEAutore implements Comparator<Libro> {

	@Override
	public int compare(Libro o1, Libro o2) {
		
		int comp = o1.getTitolo().compareTo(o2.getTitolo());
		
		if(comp == 0) {
			
			Comparator<Autore> comparatoreAutori = new ComparatoreDiAutoriPerCognomeENome();
			
			return comparatoreAutori.compare(o1.getAutore(), o2.getAutore());
		}
		
		return comp;
	}
	
	

}
