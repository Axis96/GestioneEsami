package it.dstech.repository;

import it.dstech.model.Sessione;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface SessioneRepository extends JpaRepository<Sessione, Long> {

}