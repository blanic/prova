package it.uniroma3.siw.bookclub.model;

import java.time.Year;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Entity
public class Libro {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotBlank
	@Size(min = 3, max = 30)
	private String titolo;

	@NotBlank
	@Size(min = 3, max = 3000)
	private String descrizione;

	@NotBlank
	@ManyToOne
	private Autore autore;
		
	@Size(min = 0, max = 5)
	private Year anno;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.REMOVE)
	private List<Recensione> recensioni;

	@OneToMany(mappedBy = "libro", cascade = CascadeType.REMOVE)
	private List<Incontro> incontri;

	private String immagine;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitolo() {
		return titolo;
	}

	public void setTitolo(String titolo) {
		this.titolo = titolo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Autore getAutore() {
		return autore;
	}

	public void setAutore(Autore autore) {
		this.autore = autore;
	}

	public Year getAnno() {
		return anno;
	}

	public void setAnno(Year anno) {
		this.anno = anno;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public List<Incontro> getIncontri() {
		return incontri;
	}

	public void setIncontri(List<Incontro> incontri) {
		this.incontri = incontri;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anno, autore, titolo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Libro other = (Libro) obj;
		return Objects.equals(anno, other.anno) && Objects.equals(autore, other.autore)
				&& Objects.equals(titolo, other.titolo);
	}

}