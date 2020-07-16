package it.dstech.repository;

import it.dstech.model.Esame;
import it.dstech.model.Risultato;
import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface RisultatoRepository extends JpaRepository<Risultato, Long> {
	
	
	List<Risultato> findByEsame(Esame esame);

}