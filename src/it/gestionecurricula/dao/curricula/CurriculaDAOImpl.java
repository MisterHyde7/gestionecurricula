package it.gestionecurricula.dao.curricula;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import it.gestionecurricula.dao.AbstractMySQLDAO;
import it.gestionecurricula.model.Curricula;

public class CurriculaDAOImpl extends AbstractMySQLDAO implements CurriculaDAO {

	@Override
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public List<Curricula> list() throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		ArrayList<Curricula> result = new ArrayList<Curricula>();
		Curricula curriculaTemp = null;

		try (Statement ps = connection.createStatement(); ResultSet rs = ps.executeQuery("select * from curricula")) {

			while (rs.next()) {
				curriculaTemp = new Curricula();
				curriculaTemp.setNome(rs.getString("NOME"));
				curriculaTemp.setCognome(rs.getString("COGNOME"));
				curriculaTemp.setDataDiNascita(rs.getDate("nascita"));
				curriculaTemp.setTelefono(rs.getString("telefono"));
				curriculaTemp.setEmail(rs.getString("email"));
				curriculaTemp.setId(rs.getLong("ID"));
				result.add(curriculaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public Curricula get(Long idInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (idInput == null || idInput < 1)
			throw new Exception("Valore di input non ammesso.");

		Curricula result = null;
		try (PreparedStatement ps = connection.prepareStatement("select * from curricula where id=?")) {

			ps.setLong(1, idInput);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					result = new Curricula();
					result.setNome(rs.getString("NOME"));
					result.setCognome(rs.getString("COGNOME"));
					result.setDataDiNascita(rs.getDate("nascita"));
					result.setTelefono(rs.getString("telefono"));
					result.setEmail(rs.getString("email"));
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
	public int insert(Curricula esperienzaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO curricula (nome, cognome, nascita, telefono, email) VALUES (?, ?, ?, ?, ?);")) {
			ps.setString(1, esperienzaInput.getNome());
			ps.setString(2, esperienzaInput.getCognome());
			ps.setDate(3, new java.sql.Date(esperienzaInput.getDataDiNascita().getTime()));
			ps.setString(4, esperienzaInput.getTelefono());
			ps.setString(5, esperienzaInput.getEmail());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int update(Curricula esperienzaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null || esperienzaInput.getId() == null || esperienzaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement(
				"UPDATE curricula SET nome=?, cognome=?, nascita=?, telefono=?, email=? where id=?;")) {
			ps.setString(1, esperienzaInput.getNome());
			ps.setString(2, esperienzaInput.getCognome());
			ps.setDate(3, new java.sql.Date(esperienzaInput.getDataDiNascita().getTime()));
			ps.setString(4, esperienzaInput.getTelefono());
			ps.setString(5, esperienzaInput.getEmail());
			ps.setLong(6, esperienzaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public int delete(Curricula esperienzaInput) throws Exception {
		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (esperienzaInput == null || esperienzaInput.getId() == null || esperienzaInput.getId() < 1)
			throw new Exception("Valore di input non ammesso.");

		int result = 0;
		try (PreparedStatement ps = connection.prepareStatement("DELETE FROM curricula WHERE ID=?")) {
			ps.setLong(1, esperienzaInput.getId());
			result = ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

	@Override
	public List<Curricula> findByExample(Curricula example) throws Exception {

		// prima di tutto cerchiamo di capire se possiamo effettuare le operazioni
		if (isNotActive())
			throw new Exception("Connessione non attiva. Impossibile effettuare operazioni DAO.");

		if (example == null)
			throw new Exception("Valore di input non ammesso.");

		ArrayList<Curricula> result = new ArrayList<Curricula>();
		Curricula curriculaTemp = null;

		String query = "select * from curricula where 1=1 ";

		if (example.getNome() != null && !example.getNome().isEmpty()) {
			query += " and nome like '" + example.getNome() + "%' ";
		}

		if (example.getCognome() != null && !example.getCognome().isEmpty()) {
			query += " and cognome like '" + example.getCognome() + "%' ";
		}

		if (example.getDataDiNascita() != null) {
			query += " and nascita = '" + example.getDataDiNascita() + "'";
		}

		if (example.getTelefono() != null && !example.getTelefono().isEmpty()) {
			query += " and telefono like '" + example.getTelefono() + "%' ";
		}

		if (example.getEmail() != null && !example.getEmail().isEmpty()) {
			query += " and email like '" + example.getEmail() + "%' ";
		}

		try (Statement ps = connection.createStatement()) {
			ResultSet rs = ps.executeQuery(query);

			while (rs.next()) {
				curriculaTemp = new Curricula();
				curriculaTemp.setNome(rs.getString("NOME"));
				curriculaTemp.setCognome(rs.getString("COGNOME"));
				curriculaTemp.setDataDiNascita(rs.getDate("nascita"));
				curriculaTemp.setTelefono(rs.getString("telefono"));
				curriculaTemp.setEmail(rs.getString("email"));
				curriculaTemp.setId(rs.getLong("ID"));
				result.add(curriculaTemp);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return result;
	}

}
