package it.dstech.model;

import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Studente {
	
	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "studente_id")
	private Long matricola;
	private String password;
	private String username;
	private Boolean active;
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "studente_ruolo", joinColumns = @JoinColumn(name = "studente_id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruolo;
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Esame> storicoEsami;
	
	
	
	public Long getMatricola() {
		return matricola;
	}
	public void setMatricola(Long matricola) {
		this.matricola = matricola;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	public List<Esame> getStoricoEsami() {
		return storicoEsami;
	}
	public void setStoricoEsami(List<Esame> storicoEsami) {
		this.storicoEsami = storicoEsami;
	}
	
	
}
