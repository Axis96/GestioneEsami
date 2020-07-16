package it.dstech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dstech.model.Docente;
import it.dstech.model.Esame;
import it.dstech.model.Risultato;
import it.dstech.model.Studente;
import it.dstech.repository.RisultatoRepository;

@Service
@Transactional
public class RisultatoService {

	@Autowired
	private RisultatoRepository risultatoRepository;
	
	public List<Risultato> getListaRisultati(Esame esame) {
		List<Risultato>listaRisultati = new ArrayList<Risultato>();
		
		for (Studente studente : esame.getListaStudentiPrenotati()) {
			Risultato risultatoStudente = new Risultato(esame, studente);
			listaRisultati.add(risultatoStudente);
		}
		return listaRisultati;
	}
	
	
	public void save(Risultato risultato) {
		risultatoRepository.save(risultato);
	}
	
	public List<Risultato> listaRisultati() {
		return (List<Risultato>) risultatoRepository.findAll();
	}

	public List<Risultato> listaRisultatiEsame(Esame esame) {
		return (List<Risultato>) risultatoRepository.findByEsame(esame);
	}
	
	public Risultato get(Long id) {
		return risultatoRepository.findById(id).get();
	}
}
