package it.gestionecurricula.service.esperienza;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.curricula.CurriculaDAO;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaServiceImpl implements EsperienzaService {

	private EsperienzaDAO esperienzaDao;

	private CurriculaDAO curriculaDao;

	public void setEsperienzaDao(EsperienzaDAO esperienzaDao) {
		this.esperienzaDao = esperienzaDao;
	}

	public void setCurriculaDao(CurriculaDAO curriculaDao) {
		this.curriculaDao = curriculaDao;
	}

	@Override
	public List<Esperienza> listAll() throws Exception {
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.list();

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza findById(Long idInput) throws Exception {
		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Esperienza result = null;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.get(idInput);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int aggiorna(Esperienza input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.update(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovo(Esperienza input) throws Exception {
		if (input == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.insert(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int rimuovi(Esperienza input) throws Exception {
		if (input == null || input.getId() == null || input.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.delete(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza input) throws Exception {
		List<Esperienza> result = new ArrayList<>();
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			// inietto la connection nel dao
			esperienzaDao.setConnection(connection);

			// eseguo quello che realmente devo fare
			result = esperienzaDao.findByExample(input);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int inserisciNuovaEsperienzaAlCurriculum(Esperienza input, Long id) throws Exception {

		int inserimento = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			esperienzaDao.setConnection(connection);

			curriculaDao.setConnection(connection);

			if (curriculaDao.get(id) == null)
				throw new RuntimeException("errore");

			if (curriculaDao.get(id).getListaDiEsperienze().get(curriculaDao.get(id).getListaDiEsperienze().size() - 1)
					.getDataFine().compareTo(input.getDataInizio()) > 0) {
				throw new RuntimeException("esperienza ancora in corso");
			}

			if (curriculaDao.get(id).getListaDiEsperienze().get(curriculaDao.get(id).getListaDiEsperienze().size() - 1)
					.getDataFine().compareTo(input.getDataInizio()) == 0
					|| curriculaDao.get(id).getListaDiEsperienze()
							.get(curriculaDao.get(id).getListaDiEsperienze().size() - 1).getDataFine()
							.compareTo(input.getDataInizio()) < 0) {
				if (esperienzaDao.insert(input) != 1)
					throw new RuntimeException("errore");
			}

			if (curriculaDao.get(id).getListaDiEsperienze().get(curriculaDao.get(id).getListaDiEsperienze().size() - 1)
					.getDataFine() == null) {
				if (esperienzaDao.update(input) != 1)
					throw new RuntimeException("errore");
				if (esperienzaDao.insert(input) != 1)
					throw new RuntimeException("errore");
			}

			inserimento++;

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return inserimento;
	}

}
