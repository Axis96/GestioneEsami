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
	
	private String nomeDocente;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
	@DateTimeFormat(iso = DateTimeFormat.ISO.TIME)
	private String data;
	
	@Column(nullable = true)
	private int voto;
	
	@ManyToMany(cascade = CascadeType.ALL)
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

	public String getNomeDocente() {
		return docente.getUsername();
	}

	public void setNomeDocente(String nomeDocente) {
		this.nomeDocente = nomeDocente;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public int getVoto() {
		return voto;
	}

	public void setVoto(int voto) {
		this.voto = voto;
	}

	public List<Studente> getListaStudentiPrenotati() {
		return listaStudentiPrenotati;
	}

	public void setListaStudentiPrenotati(List<Studente> listaStudentiPrenotati) {
		this.listaStudentiPrenotati = listaStudentiPrenotati;
	}

	
	

}
