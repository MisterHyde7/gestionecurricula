package it.gestionecurricula.service;

import it.gestionecurricula.dao.curricula.CurriculaDAOImpl;
import it.gestionecurricula.dao.esperienza.EsperienzaDAOImpl;
import it.gestionecurricula.service.curricula.CurriculaService;
import it.gestionecurricula.service.curricula.CurriculaServiceImpl;
import it.gestionecurricula.service.esperienza.EsperienzaService;
import it.gestionecurricula.service.esperienza.EsperienzaServiceImpl;

public class MyServiceFactory {

	public static CurriculaService getCurriculaServiceImpl() {
		CurriculaService curriculaService = new CurriculaServiceImpl();
		curriculaService.setCurriculaDao(new CurriculaDAOImpl());
		EsperienzaService esperienzaService = new EsperienzaServiceImpl();
		esperienzaService.setEsperienzaDao(new EsperienzaDAOImpl());
		return curriculaService;
	}

	public static EsperienzaService getEsperienzaServiceImpl() {
		EsperienzaService esperienzaService = new EsperienzaServiceImpl();
		esperienzaService.setEsperienzaDao(new EsperienzaDAOImpl());
		CurriculaService curriculaService = new CurriculaServiceImpl();
		curriculaService.setCurriculaDao(new CurriculaDAOImpl());
		return esperienzaService;
	}

}
