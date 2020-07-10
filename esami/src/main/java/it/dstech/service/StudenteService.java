package it.dstech.service;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import it.dstech.model.Studente;
import it.dstech.model.Studente;
import it.dstech.repository.StudenteRepository;

@Service
@Transactional
public class StudenteService {
	
	@Autowired
	StudenteRepository studenteRepository;

	
	public void save(Studente studente) {
		studenteRepository.save(studente);
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
