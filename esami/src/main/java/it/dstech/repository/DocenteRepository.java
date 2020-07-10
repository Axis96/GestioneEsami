package it.dstech.repository;

import it.dstech.model.Docente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface DocenteRepository extends JpaRepository<Docente, Long> {

	Docente findByUsername(String username);
	
}