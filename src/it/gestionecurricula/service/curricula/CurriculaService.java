package it.gestionecurricula.service.curricula;

import java.util.List;

import it.gestionecurricula.dao.curricula.CurriculaDAO;
import it.gestionecurricula.model.Curricula;

public interface CurriculaService {

	// questo mi serve per injection
	public void setCurriculaDao(CurriculaDAO curriculaDao);

	public List<Curricula> listAll() throws Exception;

	public Curricula findById(Long idInput) throws Exception;

	public int aggiorna(Curricula input) throws Exception;

	public int inserisciNuovo(Curricula input) throws Exception;

	public int rimuovi(Curricula input) throws Exception;

	public List<Curricula> findByExample(Curricula input) throws Exception;

}
