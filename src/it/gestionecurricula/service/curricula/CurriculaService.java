package it.gestionecurricula.service.curricula;

import java.util.List;

import it.gestionecurricula.dao.curricula.CurriculaDAO;
import it.gestionecurricula.model.Curricula;
import it.gestionecurricula.model.Esperienza;

public interface CurriculaService {

	// questo mi serve per injection
	public void setCurriculaDao(CurriculaDAO curriculaDao);

	public List<Curricula> listAll() throws Exception;

	public Curricula findById(Long idInput) throws Exception;

	public int aggiorna(Curricula input) throws Exception;

	public int inserisciNuovo(Curricula input) throws Exception;

	public int rimuovi(Curricula input) throws Exception;

	public List<Curricula> findByExample(Curricula input) throws Exception;

	public int inserisciNuovaEsperienzaAlCurriculum(Esperienza input) throws Exception;

	public int rimuoviCurriculumDaDatabase(Esperienza input) throws Exception;

}
