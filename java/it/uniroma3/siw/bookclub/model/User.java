package it.uniroma3.siw.bookclub.model;

import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Size(min = 3, max = 30)
	private String nome;
	
	@Size(min = 3, max = 30)
	private String cognome;
	
	@Size(min = 0, max = 3000)
	private String presentazione;
	
	private String immagine;
	
	@OneToMany(mappedBy = "autore", cascade = CascadeType.REMOVE)
	private List<Commento> commenti;
	
	@OneToMany(mappedBy = "autore", cascade = CascadeType.REMOVE)
	private List<Recensione> recensioni;
	
	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	private Credentials credentials;

	@ManyToMany(mappedBy = "partecipanti")
	private List<Incontro> partecipazioni;
	
	
	
	
	public List<Incontro> getPartecipazioni() {
		return partecipazioni;
	}

	public void setPartecipazioni(List<Incontro> partecipazioni) {
		this.partecipazioni = partecipazioni;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getPresentazione() {
		return presentazione;
	}

	public void setPresentazione(String presentazione) {
		this.presentazione = presentazione;
	}

	public String getImmagine() {
		return immagine;
	}

	public void setImmagine(String immagine) {
		this.immagine = immagine;
	}

	public List<Commento> getCommenti() {
		return commenti;
	}

	public void setCommenti(List<Commento> commenti) {
		this.commenti = commenti;
	}

	public List<Recensione> getRecensioni() {
		return recensioni;
	}

	public void setRecensioni(List<Recensione> recensioni) {
		this.recensioni = recensioni;
	}

	public Credentials getCredentials() {
		return credentials;
	}

	public void setCredentials(Credentials credentials) {
		this.credentials = credentials;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, credentials, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(credentials, other.credentials)
				&& Objects.equals(nome, other.nome);
	}

	
	
	
}
