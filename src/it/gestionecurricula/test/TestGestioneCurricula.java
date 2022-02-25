package it.gestionecurricula.test;

import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.model.Curricula;
import it.gestionecurricula.model.Esperienza;
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

			testInsertEsperienza(esperienzaService);

			testRemoveEsperienza(esperienzaService);

			testInserisciNuovaEsperienza(esperienzaService, curriculaService);

			testRimuoviCurriculumSeVuoto(curriculaService);

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
		List<Curricula> interoContenutoTabella = curriculaService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("Non ho nulla da rimuovere");
		Long idDelPrimo = interoContenutoTabella.get(0).getId();
		Curricula toBeRemoved = curriculaService.findById(idDelPrimo);
		if (curriculaService.rimuovi(toBeRemoved) != 1)
			throw new RuntimeException("testRimozioneUser FAILED ");
		System.out.println("======= Fine test =======");
	}

	private static void testInsertEsperienza(EsperienzaService esperienzaService) throws Exception {
		System.out.println("======= Inizio test =======");
		List<Esperienza> listaDiCurriculumPresenti = esperienzaService.listAll();
		if (listaDiCurriculumPresenti == null)
			throw new RuntimeException("ERRORE");
		Esperienza nuovaCurricula = new Esperienza(1L, "test descrizione", new java.util.Date(2022, 01, 01),
				new java.util.Date(2022, 03, 01), "test conoscenze acquisite");
		if (esperienzaService.inserisciNuovo(nuovaCurricula) != 1)
			throw new RuntimeException("ERRORE");
		System.out.println("======= Fine test =======");
	}

	private static void testRemoveEsperienza(EsperienzaService esperienzaService) throws Exception {
		System.out.println("======= Inizio test =======");
		List<Esperienza> listaDiCurriculumPresenti = esperienzaService.listAll();
		if (listaDiCurriculumPresenti == null)
			throw new RuntimeException("ERRORE");
		List<Esperienza> interoContenutoTabella = esperienzaService.listAll();
		if (interoContenutoTabella.isEmpty() || interoContenutoTabella.get(0) == null)
			throw new Exception("Non ho nulla da rimuovere");
		Long idDelPrimo = interoContenutoTabella.get(0).getId();
		Esperienza toBeRemoved = esperienzaService.findById(idDelPrimo);
		if (esperienzaService.rimuovi(toBeRemoved) != 1)
			throw new RuntimeException("testRimozioneUser FAILED ");
		System.out.println("======= Fine test =======");
	}

	public static void testInserisciNuovaEsperienza(EsperienzaService esperienzaService,
			CurriculaService curriculaService) throws Exception {
		System.out.println("======= Inizio test =======");
		List<Curricula> listaDiCurriculumPresenti = curriculaService.listAll();
		if (listaDiCurriculumPresenti == null)
			throw new RuntimeException("ERRORE");
		Esperienza nuovaEsperienza = new Esperienza(3L, "test nuova esperienza", new java.util.Date(2022, 01, 01),
				new java.util.Date(2022, 02, 28), "test conoscenze");
		Curricula nuovaCurricula = new Curricula(7L, "test curricula", "test curricula",
				new java.util.Date(2000, 01, 01), "123456789", "test@test.it");
		curriculaService.inserisciNuovo(nuovaCurricula);
		nuovaEsperienza.setCurriculum(curriculaService
				.findById(curriculaService.listAll().get(curriculaService.listAll().size() - 1).getId()));
		if (esperienzaService.inserisciNuovaEsperienzaAlCurriculum(nuovaEsperienza, nuovaCurricula.getId()) < 0)
			throw new RuntimeException("ERRORE");
		System.out.println("======= Fine test =======");
	}

	private static void testRimuoviCurriculumSeVuoto(CurriculaService curriculaService) throws Exception {
		System.out.println("======= Inizio test =======");
		List<Curricula> listaDiCurriculumPresenti = curriculaService.listAll();
		if (listaDiCurriculumPresenti == null)
			throw new RuntimeException("ERRORE");
		Curricula nuovaCurricula = new Curricula(100L, "test remove curricula", "test remove curricula",
				new java.util.Date(2000, 01, 01), "123456789", "test@test.it");
		nuovaCurricula.setListaDiEsperienze(new ArrayList<Esperienza>());
		if (curriculaService.inserisciNuovo(nuovaCurricula) != 1)
			throw new RuntimeException("ERRORE");
		if (curriculaService.rimuoviCurriculumDaDatabase(curriculaService, nuovaCurricula) != 1)
			throw new RuntimeException("ERRORE");
		System.out.println("======= Fine test =======");
	}

}