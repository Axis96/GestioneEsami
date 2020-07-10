package it.dstech.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Docente {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "user_id")
	private Long matricola;
	
	private String username;
	private String password;
	private Boolean active;
	
	@ManyToMany(cascade = CascadeType.MERGE)
    private Set<Ruolo> ruolo;
	
	@ManyToMany
	List<Esame> listaEsami = new ArrayList<Esame>();
	

	public Long getMatricola() {
		return matricola;
	}

	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Set<Ruolo> getRuolo() {
		return ruolo;
	}

	public void setRuolo(Set<Ruolo> ruolo) {
		this.ruolo = ruolo;
	}

	public List<Esame> getListaEsami() {
		return listaEsami;
	}

	public void setListaEsami(List<Esame> listaEsami) {
		this.listaEsami = listaEsami;
	}
	
}
