package it.dstech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.dstech.model.Docente;
import it.dstech.repository.DocenteRepository;

@Service
@Transactional
public class DocenteService {
	@Autowired
	DocenteRepository docenteRepository;

	
	public void save(Docente docente) {
		docenteRepository.save(docente);
	}
	
	public Docente get(Long id) {
		return docenteRepository.findById(id).get();
	}

	public void delete(Long id) {
		docenteRepository.deleteById(id);
	}
	
	public List<Docente> listaDocenti() {
		return (List<Docente>) docenteRepository.findAll();
	}

	public Docente findUserByUsername(String username) {
		return docenteRepository.findByUsername(username);
	}
}
