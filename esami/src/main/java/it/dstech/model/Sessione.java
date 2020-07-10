package it.dstech.model;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;

public class Sessione {

	private Timestamp dataInizio;
	private Timestamp dataFine;
	
	@ManyToMany
	List<Esame> esamiPrenotabili= new ArrayList<Esame>();
	
}
