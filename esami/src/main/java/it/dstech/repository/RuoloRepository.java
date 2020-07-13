package it.dstech.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import it.dstech.model.Ruolo;

public interface RuoloRepository extends JpaRepository<Ruolo, Long> {
	Ruolo findByRuolo(String ruolo);
}
