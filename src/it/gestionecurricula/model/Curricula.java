package it.gestionecurricula.model;

import java.util.Date;
import java.util.List;

public class Curricula {

	private Long id;
	private String nome;
	private String cognome;
	private Date dataDiNascita;
	private String telefono;
	private String email;
	private List<Esperienza> listaDiEsperienze;

	public Curricula() {
		super();
	}

	public Curricula(Long id, String nome, String cognome, Date dataDiNascita, String telefono, String email) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.telefono = telefono;
		this.email = email;
	}

	public Curricula(Long id, String nome, String cognome, Date dataDiNascita, String telefono, String email,
			List<Esperienza> listaDiEsperienze) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.dataDiNascita = dataDiNascita;
		this.telefono = telefono;
		this.email = email;
		this.listaDiEsperienze = listaDiEsperienze;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Date getDataDiNascita() {
		return dataDiNascita;
	}

	public void setDataDiNascita(Date dataDiNascita) {
		this.dataDiNascita = dataDiNascita;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Esperienza> getListaDiEsperienze() {
		return listaDiEsperienze;
	}

	public void setListaDiEsperienze(List<Esperienza> listaDiEsperienze) {
		this.listaDiEsperienze = listaDiEsperienze;
	}

}
