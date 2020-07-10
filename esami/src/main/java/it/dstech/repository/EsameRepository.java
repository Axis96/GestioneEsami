package it.dstech.repository;

import it.dstech.model.Esame;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EsameRepository extends JpaRepository<Esame, Long> {

}