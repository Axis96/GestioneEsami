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
	private EsameRepository esameRepository;
	
	@Autowired
	private StudenteService studenteService;

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
	
	
	public String reversedDateFormat(Esame esame) {
		String anno = esame.getData().substring(0, 4);
		String mese = esame.getData().substring(5, 7);
		String giorno = esame.getData().substring(8, 10);
		String data = giorno + "-" + mese + "-" + anno;
		return data;
	}
	
	public boolean checkEsame(Esame esameDaControllare, Studente studente) {
		for (Studente studentePrenotato : esameDaControllare.getListaStudentiPrenotati() /* || studenteservice.controlloSessione(Esame esameDaControllare, Studente studente) */) {
			if (studentePrenotato == studente ) {
				return true;
			}
		}
		return false;
	}
	
	public List<Esame> listaEsamiDaPrenotare(Long matricola) {
		List<Esame> lista = new ArrayList<Esame>();
		for (Esame esame : listaEsami()) {
			if (esame.getListaStudentiPrenotati() == null) {
				lista.add(esame);
			} else {
				for (Studente studente : esame.getListaStudentiPrenotati()) {
					if (studente.getMatricola() != matricola || studente.getMatricola() == null) {
						lista.add(esame);
					}
				}
			}
		}
		return lista;
	}
}
