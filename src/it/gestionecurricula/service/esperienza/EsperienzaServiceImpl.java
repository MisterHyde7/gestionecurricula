package it.gestionecurricula.service.esperienza;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import it.gestionecurricula.connection.MyConnection;
import it.gestionecurricula.dao.Constants;
import it.gestionecurricula.dao.esperienza.EsperienzaDAO;
import it.gestionecurricula.model.Esperienza;
import it.gestionecurricula.service.curricula.CurriculaService;

public class EsperienzaServiceImpl implements EsperienzaService {

	private EsperienzaDAO esperienzaDao;

	public void setEsperienzaDao(EsperienzaDAO esperienzaDao) {
		this.esperienzaDao = esperienzaDao;
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
	public int inserisciNuovaEsperienzaAlCurriculum(Esperienza input, CurriculaService curriculaService)
			throws Exception {
		List<Date> result = new ArrayList<Date>();
		int inserimento = 0;
		try (Connection connection = MyConnection.getConnection(Constants.DRIVER_NAME, Constants.CONNECTION_URL)) {

			esperienzaDao.setConnection(connection);

			result = esperienzaDao.cercaEsperienzePassate(input);

			if (result.get(result.size() - 1).compareTo(input.getDataInizio()) > 0) {
				throw new RuntimeException("esperienza ancora in corso");
			}

			if (result.get(result.size() - 1).compareTo(input.getDataInizio()) == 0
					|| result.get(result.size() - 1).compareTo(input.getDataInizio()) < 0) {
				if (esperienzaDao.insert(input) != 1)
					throw new RuntimeException("errore");
			}

			if (result.get(result.size() - 1) == null) {
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
