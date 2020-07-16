package it.dstech.repository;

import it.dstech.model.Docente;
import it.dstech.model.Esame;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EsameRepository extends JpaRepository<Esame, Long> {

	List<Esame> findByDocente(Docente docente);

	
}