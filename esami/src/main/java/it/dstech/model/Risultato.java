package it.dstech.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Risultato {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "esame_id")
	private Long id;
	
	private Esame esame;
	
	private Studente studente;
	
	private Long matricolaStudente;
	
	private String nomeStudente;
	
	private int voto;
	
	
	
	public Risultato(Esame esame, Studente studente) {
		super();
		this.esame = esame;
		this.studente = studente;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Esame getEsame() {
		return esame;
	}
	public void setEsame(Esame esame) {
		this.esame = esame;
	}
	public Studente getStudente() {
		return studente;
	}
	public void setStudente(Studente studente) {
		this.studente = studente;
	}
	public int getVoto() {
		return voto;
	}
	public void setVoto(int voto) {
		this.voto = voto;
	}

	public Long getMatricolaStudente() {
		return studente.getMatricola();
	}

	public void setMatricolaStudente(Long matricolaStudente) {
		this.matricolaStudente = matricolaStudente;
	}

	public String getNomeStudente() {
		return studente.getUsername();
	}

	public void setNomeStudente(String nomeStudente) {
		this.nomeStudente = nomeStudente;
	}
	
	
}
