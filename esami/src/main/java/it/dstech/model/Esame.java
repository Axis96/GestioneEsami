package it.dstech.model;

import java.security.Timestamp;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;



@Entity
public class Esame {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "esame_id")
	private Long id;
	
	private String nome;
	
	@ManyToMany
	private Docente docente;
	
	private Studente studente;
	
	private Timestamp data;
	private int voto;
	
	@ManyToMany
	private Sessione sessione;
	
	private List<Studente> listaStudentiPrenotati;
	
	
	

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

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	public Studente getStudente() {
		return studente;
	}

	public void setStudente(Studente studente) {
		this.studente = studente;
	}

	public Timestamp getData() {
		return data;
	}

	public void setData(Timestamp data) {
		this.data = data;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public Sessione getSessione() {
		return sessione;
	}

	public void setSessione(Sessione sessione) {
		this.sessione = sessione;
	}

	public List<Studente> getListaStudentiPrenotati() {
		return listaStudentiPrenotati;
	}

	public void setListaStudentiPrenotati(List<Studente> listaStudentiPrenotati) {
		this.listaStudentiPrenotati = listaStudentiPrenotati;
	}
	
	
	
}
