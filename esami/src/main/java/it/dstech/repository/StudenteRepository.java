package it.dstech.repository;

import it.dstech.model.Studente;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface StudenteRepository extends JpaRepository<Studente, Long> {

	Studente findByUsername(String username);

}