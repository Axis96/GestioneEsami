package it.dstech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import it.dstech.model.Esame;
import it.dstech.model.Risultato;

@Repository
public interface RisultatoRepository extends JpaRepository<Risultato, Long> {
	
	
	List<Risultato> findByEsame(Esame esame);
	
	@Query(value ="select r from Risultato r where studente.matricola = :studente order by voto ASC")
	List<Risultato> sortVotoAsc(@Param("studente") Long studente);
	@Query(value ="select r from Risultato r where studente.matricola = :studente order by voto DESC")
	List<Risultato> sortVotoDesc(@Param("studente") Long studente);
	
//	@Query(value ="select r, count(*) from Risultato r where studente.matricola = :studente group by esame order by count(*) DESC FETCH FIRST 1 ROW ONLY")
//	Risultato bocciato(@Param("studente") Long studente);
}