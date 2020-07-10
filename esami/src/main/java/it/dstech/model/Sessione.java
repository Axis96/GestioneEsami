package it.dstech.model;

import java.security.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.ManyToMany;

public class Sessione {

	private Timestamp dataInizio;
	private Timestamp dataFine;
	
	@ManyToMany
	private List<Esame> esamiPrenotabili;
	
}
