package it.uniroma3.siw.bookclub.model;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Commento {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 300)
	private String testo;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private User autore;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Recensione recensione;
	
	private LocalDateTime dataOrario;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTesto() {
		return testo;
	}

	public void setTesto(String testo) {
		this.testo = testo;
	}

	public User getAutore() {
		return autore;
	}

	public void setAutore(User autore) {
		this.autore = autore;
	}

	public Recensione getRecensione() {
		return recensione;
	}

	public void setRecensione(Recensione recensione) {
		this.recensione = recensione;
	}

	public LocalDateTime getDataOrario() {
		return dataOrario;
	}

	public void setDataOrario(LocalDateTime dataOrario) {
		this.dataOrario = dataOrario;
	}

	@Override
	public int hashCode() {
		return Objects.hash(autore, dataOrario, recensione, testo);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Commento other = (Commento) obj;
		return Objects.equals(autore, other.autore) && Objects.equals(dataOrario, other.dataOrario)
				&& Objects.equals(recensione, other.recensione) && Objects.equals(testo, other.testo);
	}

	
	
	
}