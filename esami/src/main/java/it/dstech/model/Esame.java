package it.dstech.model;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

public class Esame {

	private String nome;
	
	@OneToMany
	private Docente docente;
	
	private Studente studente;
	
	private Timestamp data;
	private int voto;
	
	@ManyToMany
	private Sessione sessione;
	
	List<Studente> listaStudentiPrenotati = new ArrayList<Studente>();
	
}
