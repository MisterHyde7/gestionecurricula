package it.gestionecurricula.service.curricula;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.curricula.CurriculaDAO;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Curricula;
import it.gestionecurricula.model.Esperienza;

public class CurriculaServiceImpl implements CurriculaService {

	private CurriculaDAO curriculaDao;
	
	private EsperienzaDAO esperienzaDao;

	public void setEsperienzaDao(EsperienzaDAO esperienzaDao) {
		this.esperienzaDao = esperienzaDao;
	}

	public void setCurriculaDao(CurriculaDAO curriculaDao) {
		this.curriculaDao = curriculaDao;
	}

	@Override
	public List<Curricula> listAll() throws Exception {
		List<Curricula> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curricula findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Curricula result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculaDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Curricula input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculaDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Curricula input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculaDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Curricula input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculaDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Curricula> findByExample(Curricula input) throws Exception {
		List<Curricula> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = curriculaDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuoviCurriculumDaDatabase(CurriculaService curriculaService, Curricula input) throws Exception {
		int esitoRimozione = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			curriculaDao.setConnection(connection);

			List<Esperienza> nuovaList = new ArrayList<>();
			curriculaDao.list().get(curriculaDao.list().size() - 1).setListaDiEsperienze(nuovaList);
			if (!(curriculaDao.list().get(curriculaDao.list().size() - 1).getListaDiEsperienze().isEmpty()))
				throw new RuntimeException("esperienze presenti");

			if (curriculaDao.delete(input) != 1)
				throw new RuntimeException("ERRORE");

			esitoRimozione++;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return esitoRimozione;
	}

}
