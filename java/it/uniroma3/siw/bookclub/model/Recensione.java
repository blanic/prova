package it.uniroma3.siw.bookclub.model;

import java.time.LocalDateTime;
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
public class Recensione {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 30)
	private String titolo;
	
	@NotBlank
	@Size(min = 50, max = 3000)
	private String testo;
	
	private LocalDateTime dataOrario;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User autore;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Libro libro;
	
	@OneToMany(mappedBy = "recensione", cascade = CascadeType.REMOVE)
	private List<Commento> commenti;

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

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public LocalDateTime getDataOrario() {
		return dataOrario;
	}

	public void setDataOrario(LocalDateTime dataOrario) {
		this.dataOrario = dataOrario;
	}

	public User getAutore() {
		return autore;
	}

	public void setAutore(User user) {
		this.autore = user;
	}

	public Libro getLibro() {
		return libro;
	}

	public void setLibro(Libro libro) {
		this.libro = libro;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autore, libro);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Recensione other = (Recensione) obj;
		return Objects.equals(autore, other.autore) && Objects.equals(libro, other.libro);
	}

	

	
	
	
}