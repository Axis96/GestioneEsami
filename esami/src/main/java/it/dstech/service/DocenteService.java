package it.dstech.service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import it.dstech.model.Docente;
import it.dstech.model.Ruolo;
import it.dstech.repository.DocenteRepository;
import it.dstech.repository.RuoloRepository;

@Service
@Transactional
public class DocenteService {
	
	private DocenteRepository docenteRepository;
	private RuoloRepository ruoloRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
    public DocenteService(DocenteRepository docenteRepository,
                       RuoloRepository ruoloRepository,
                       BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.docenteRepository = docenteRepository;
        this.ruoloRepository = ruoloRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }
	 public Docente save(Docente docente) {
	        docente.setPassword(bCryptPasswordEncoder.encode(docente.getPassword()));
	        docente.setActive(true);
	       
	        Ruolo userRole = ruoloRepository.findByRuolo("DOCENTE");
	        docente.setRuolo(new HashSet<Ruolo>(Arrays.asList(userRole)));
	        
	        return docenteRepository.save(docente);
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
