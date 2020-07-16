package it.dstech.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.dstech.repository.RuoloRepository;
import it.dstech.model.Esame;
import it.dstech.model.Risultato;
import it.dstech.model.Ruolo;
import it.dstech.model.Studente;
import it.dstech.repository.StudenteRepository;


@Service
public class StudenteService {
	
	@Autowired
	private StudenteRepository studenteRepository;
	@Autowired
	private RuoloRepository ruoloRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	public Studente save(Studente studente) {
		Studente studente2 = studenteRepository.findByUsername(studente.getUsername());
		
		if(studente2==null) {
	        studente.setPassword(bCryptPasswordEncoder.encode(studente.getPassword()));
	        studente.setActive(true);
	       
	        Ruolo userRole = ruoloRepository.findByRuolo("STUDENTE");
	        studente.setRuolo(new HashSet<Ruolo>(Arrays.asList(userRole)));
		}
	        return studenteRepository.save(studente);
	    }
	
	
	public Studente get(Long id) {
		return studenteRepository.findById(id).get();
	}

	public void delete(Long id) {
		studenteRepository.deleteById(id);
	}
	
	public List<Studente> listaStudenti() {
		return (List<Studente>) studenteRepository.findAll();
	}

	public Studente findUserByUsername(String username) {
		return studenteRepository.findByUsername(username);
	}
	
	public double mediaVoti(Studente studente) {
	double tot=0;
	double cont=0;
		for(Esame esame :studente.getEsamiPrenotati()) {
			for(Risultato risultato :esame.getListaRisultati()) {
				if(risultato.getStudente().getMatricola()==studente.getMatricola()) {
			tot+=(double)risultato.getVoto();	
			System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\"+risultato.getVoto());
				cont++;
				}
			}
		}
	return	 tot/cont;
	}
}
