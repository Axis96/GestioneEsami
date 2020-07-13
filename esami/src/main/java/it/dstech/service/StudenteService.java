package it.dstech.service;


import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import it.dstech.repository.RuoloRepository;
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
	        studente.setPassword(bCryptPasswordEncoder.encode(studente.getPassword()));
	        studente.setActive(true);
	       
	        Ruolo userRole = ruoloRepository.findByRuolo("STUDENTE");
	        studente.setRuolo(new HashSet<Ruolo>(Arrays.asList(userRole)));
	        
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
}
