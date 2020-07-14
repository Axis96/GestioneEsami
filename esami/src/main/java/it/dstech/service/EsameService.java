package it.dstech.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.dstech.model.Esame;
import it.dstech.model.Studente;
import it.dstech.repository.EsameRepository;

@Service
@Transactional
public class EsameService {

	@Autowired
	EsameRepository esameRepository;

	public void save(Esame esame) {
		esameRepository.save(esame);
	}

	public Esame get(Long id) {
		return esameRepository.findById(id).get();
	}

	public void delete(Long id) {
		esameRepository.deleteById(id);
	}

	public List<Esame> listaEsami() {
		return (List<Esame>) esameRepository.findAll();
	}

	public List<Esame> listaEsamiDaPrenotare(Long matricola) {
		List<Esame> listaEsami = new ArrayList<Esame>();
		for (Esame esame : listaEsami()) {
			for (Studente studente : esame.getListaStudentiPrenotati()) {
				if (studente.getMatricola() != matricola) {
					listaEsami.add(esame);
				}
			}
		}
		return listaEsami;
	}
	
	
}
