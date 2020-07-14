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
import javax.persistence.OneToMany;

@Entity
public class Docente {

	@Id
	@GeneratedValue(strategy= GenerationType.AUTO)
	@Column(name = "docente_id")
	private Long matricola;
	private String username;
	private String password;
	private Boolean active;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "docente_ruolo", joinColumns = @JoinColumn(name = "docente_id"), inverseJoinColumns = @JoinColumn(name = "ruolo_id"))
    private Set<Ruolo> ruolo;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<Esame> listaEsami;
	

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

	@Override
	public String toString() {
		return "Docente [matricola=" + matricola + ", username=" + username + ", password=" + password + ", active="
				+ active + ", ruolo=" + ruolo + ", listaEsami=" + listaEsami + "]";
	}
	
	
}
