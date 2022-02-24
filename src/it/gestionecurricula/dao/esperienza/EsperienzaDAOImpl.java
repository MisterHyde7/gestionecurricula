package it.gestionecurricula.dao.esperienza;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Esperienza;

public class EsperienzaDAOImpl extends AbstractMySQLDAO implements EsperienzaDAO {

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Esperienza> list() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Esperienza> result = new ArrayList<Esperienza>();
		Esperienza esperienzaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from esperienza")) {

			while (rs.next()) {
				esperienzaTemp = new Esperienza();
				esperienzaTemp.setDescrizione(rs.getString("descrizione"));
				esperienzaTemp.setDataInizio(rs.getDate("dataInizio"));
				esperienzaTemp.setDataFine(rs.getDate("dataFine"));
				esperienzaTemp.setConoscenzeAcquisite(rs.getString("conoscenzeAcquisite"));
				esperienzaTemp.setId(rs.getLong("ID"));
				result.add(esperienzaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Esperienza get(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Esperienza result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from esperienza where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Esperienza();
					result.setDescrizione(rs.getString("descrizione"));
					result.setDataInizio(rs.getDate("dataInizio"));
					result.setDataFine(rs.getDate("dataFine"));
					result.setConoscenzeAcquisite(rs.getString("conoscenzeAcquisite"));
					result.setId(rs.getLong("ID"));
					result.setId(rs.getLong("ID"));
				} else {
					result = null;
				}
			} // niente catch qui

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int insert(Esperienza esperienzaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO esperienza (descrizione, dataInizio, dataFine, conoscenzeAcquisite) VALUES (?, ?, ?, ?);")) {
			ps.setString(1, esperienzaInput.getDescrizione());
			ps.setDate(2, new java.sql.Date(esperienzaInput.getDataInizio().getTime()));
			ps.setDate(3, new java.sql.Date(esperienzaInput.getDataFine().getTime()));
			ps.setString(4, esperienzaInput.getConoscenzeAcquisite());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Esperienza esperienzaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null || esperienzaInput.getId() == null || esperienzaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE esperienza SET descrizione=?, dataInizio=?, dataFine=?, conoscenzeAcquisite=? where id=?;")) {
			ps.setString(1, esperienzaInput.getDescrizione());
			ps.setDate(2, new java.sql.Date(esperienzaInput.getDataInizio().getTime()));
			ps.setDate(3, new java.sql.Date(esperienzaInput.getDataFine().getTime()));
			ps.setString(4, esperienzaInput.getConoscenzeAcquisite());
			ps.setLong(5, esperienzaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Esperienza esperienzaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null || esperienzaInput.getId() == null || esperienzaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM esperienza WHERE ID=?")) {
			ps.setLong(1, esperienzaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Esperienza> findByExample(Esperienza example) throws Exception {

		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (example == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Esperienza> result = new ArrayList<Esperienza>();
		Esperienza esperienzaTemp = null;

		String query = "select * from esperienza where 1=1 ";

		if (example.getDescrizione() != null && !example.getDescrizione().isEmpty()) {
			query += " and descrizione like '" + example.getDescrizione() + "%' ";
		}

		if (example.getDataInizio() != null) {
			query += " and dataInizio = '" + example.getDataInizio() + "' ";
		}

		if (example.getDataFine() != null) {
			query += " and dataFine = '" + example.getDataFine() + "' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				esperienzaTemp = new Esperienza();
				esperienzaTemp.setDescrizione(rs.getString("descrizione"));
				esperienzaTemp.setDataInizio(rs.getDate("dataInizio"));
				esperienzaTemp.setDataFine(rs.getDate("dataFine"));
				esperienzaTemp.setConoscenzeAcquisite(rs.getString("conoscenzeAcquisite"));
				esperienzaTemp.setId(rs.getLong("ID"));
				result.add(esperienzaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
