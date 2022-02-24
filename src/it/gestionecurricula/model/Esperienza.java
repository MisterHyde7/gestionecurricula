package it.gestionecurricula.model;

import java.util.Date;

public class Esperienza {

	private Long id;
	private String descrizione;
	private Date dataInizio;
	private Date dataFine;
	private String conoscenzeAcquisite;
	private Curricula curricula;

	public Esperienza() {
		super();
	}

	public Esperienza(Long id, String descrizione, Date dataInizio, Date dataFine, String conoscenzeAcquisite) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.conoscenzeAcquisite = conoscenzeAcquisite;
	}

	public Esperienza(Long id, String descrizione, Date dataInizio, Date dataFine, String conoscenzeAcquisite,
			Curricula curricula) {
		super();
		this.id = id;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFine = dataFine;
		this.conoscenzeAcquisite = conoscenzeAcquisite;
		this.curricula = curricula;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public Date getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(Date dataInizio) {
		this.dataInizio = dataInizio;
	}

	public Date getDataInizio() {
		return dataFine;
	}

	public void setDataFine(Date dataFine) {
		this.dataFine = dataFine;
	}

	public String getConoscenzeAcquisite() {
		return conoscenzeAcquisite;
	}

	public void setConoscenzeAcquisite(String conoscenzeAcquisite) {
		this.conoscenzeAcquisite = conoscenzeAcquisite;
	}

	public Curricula getCurriculum() {
		return curricula;
	}

	public void setCurriculum(Curricula curricula) {
		this.curricula = curricula;
	}

}
