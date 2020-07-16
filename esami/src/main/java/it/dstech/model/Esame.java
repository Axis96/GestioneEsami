package it.dstech.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;



@Entity
public class Esame {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "esame_id")
	private Long id;
	
	private String nome;
	
	@ManyToOne(cascade = CascadeType.ALL)
	private Docente docente;
	
	

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private String data;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Risultato>listaRisultati;
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Studente> listaStudentiPrenotati;
	
	private String nomeDocente;
	
	private int sessione =1;

	

	public int getSessione() {
		return sessione;
	}



	public void setSessione(int sessione) {
		this.sessione = sessione;
	}



	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}



	public String getNomeDocente() {
		return docente.getUsername();
	}

	

	public List<Risultato> getListaRisultati() {
		return listaRisultati;
	}

	public void setListaRisultati(List<Risultato> listaRisultati) {
		this.listaRisultati = listaRisultati;
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

	public Docente getDocente() {
		return docente;
	}

	public void setDocente(Docente docente) {
		this.docente = docente;
	}

	

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}


	public List<Studente> getListaStudentiPrenotati() {
		return listaStudentiPrenotati;
	}

	public void setListaStudentiPrenotati(List<Studente> listaStudentiPrenotati) {
		this.listaStudentiPrenotati = listaStudentiPrenotati;
	}

	
	

}
