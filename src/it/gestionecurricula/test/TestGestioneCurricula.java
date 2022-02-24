package it.gestionecurricula.test;

import java.util.List;

import it.gestionecurricula.model.Curricula;
import it.gestionecurricula.service.MyServiceFactory;
import it.gestionecurricula.service.curricula.CurriculaService;
import it.gestionecurricula.service.esperienza.EsperienzaService;

public class TestGestioneCurricula {

	public static void main(String[] args) {

		CurriculaService curriculaService = MyServiceFactory.getCurriculaServiceImpl();
		EsperienzaService esperienzaService = MyServiceFactory.getEsperienzaServiceImpl();

		try {

			testInsertCurricula(curriculaService);
			
			testRemoveCurricula(curriculaService);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private static void testInsertCurricula(CurriculaService curriculaService) throws Exception {
		System.out.println("======= Inizio test =======");
		List<Curricula> listaDiCurriculumPresenti = curriculaService.listAll();
		if (listaDiCurriculumPresenti == null)
			throw new RuntimeException("ERRORE");
		Curricula nuovaCurricula = new Curricula(1L, "test curricula insert", "test curricula insert",
				new java.util.Date(2000, 01, 01), "123456789", "test@test.it");
		if (curriculaService.inserisciNuovo(nuovaCurricula) != 1)
			throw new RuntimeException("ERRORE");
		System.out.println("======= Fine test =======");
	}

	private static void testRemoveCurricula(CurriculaService curriculaService) throws Exception {
		System.out.println("======= Inizio test =======");
		List<Curricula> listaDiCurriculumPresenti = curriculaService.listAll();
		if (listaDiCurriculumPresenti == null)
			throw new RuntimeException("ERRORE");
		Curricula nuovaCurricula = new Curricula(2L, "test curricula remove", "test curricula remove",
				new java.util.Date(2000, 01, 01), "123456789", "test@test.it");
		if (curriculaService.inserisciNuovo(nuovaCurricula) != 1)
			throw new RuntimeException("ERRORE");
		if (curriculaService.rimuovi(nuovaCurricula) != 1)
			throw new RuntimeException("ERRORE");
		System.out.println("======= Fine test =======");
	}

}
