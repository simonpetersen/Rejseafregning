package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class MedarbejderDAO extends RemoteServiceServlet implements IMedarbejderDAO {

	private static final long serialVersionUID = 1L;

	private PreparedStatement getMedarbejderStmt = null;
	private PreparedStatement getMedarbejderListStmt = null;
	private PreparedStatement getMedarbejderSumStmt = null;
	private PreparedStatement createMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderBrugerStmt = null;
	private PreparedStatement deleteMedarbejderStmt = null;

	public MedarbejderDAO() throws Exception {

		new Connector();

		// getMedarbejder statement
		getMedarbejderStmt = Connector.conn.prepareStatement("SELECT * FROM medarbejder WHERE brugernavn = ?");

		// getMedarbejderList statement
		getMedarbejderListStmt = Connector.conn.prepareStatement("SELECT * FROM medarbejder");

		// getMedarbejderSum statement
		getMedarbejderSumStmt = Connector.conn.prepareStatement("SELECT COUNT(brugernavn) AS 'antal' FROM medarbejder");

		// createMedarbejder statement
		createMedarbejderStmt = Connector.conn.prepareStatement(
				"INSERT INTO medarbejder (brugernavn, navn, email, adgangskode, afdeling, postnr, vejnavn, husnr, etage, doer) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		// updateMedarbejder statement
		updateMedarbejderStmt = Connector.conn.prepareStatement(
				"UPDATE medarbejder SET navn = ?, email = ?, adgangskode = ?, afdeling = ?, postnr = ?, vejnavn = ?, husnr = ?, etage = ?, doer = ? "
						+ "WHERE brugernavn = ?");

		updateMedarbejderBrugerStmt = Connector.conn
				.prepareStatement("UPDATE medarbejder SET navn = ?, adgangskode = ?, email = ? WHERE brugernavn = ?");

		// deleteMedarbejder statement
		deleteMedarbejderStmt = Connector.conn.prepareStatement("DELETE FROM medarbejder WHERE brugernavn = ?");
	}

	@Override
	public MedarbejderDTO login(String brugernavn, String adgangskode) throws DALException {
		MedarbejderDTO bruger = getMedarbejder(brugernavn);
		if (!bruger.getAdgangskode().equals(adgangskode))
			throw new DALException("Forkert adgangskode");
		return bruger;
	}

	@Override
	public MedarbejderDTO getMedarbejder(String Brugernavn) throws DALException {
		ResultSet rs = null;
		try {
			getMedarbejderStmt.setString(1, Brugernavn);
			rs = getMedarbejderStmt.executeQuery();
			if (rs.first()) {
				return new MedarbejderDTO(rs.getString("navn"), rs.getString("brugernavn"), rs.getString("adgangskode"),
						rs.getString("Email"), rs.getString("afdeling"), rs.getString("postnr"),
						rs.getString("vejnavn"), rs.getString("husnr"), rs.getString("etage"), rs.getString("doer"));
			}
			throw new DALException("Medarbejder findes ikke!");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public List<MedarbejderDTO> getMedarbejderList() throws DALException {
		List<MedarbejderDTO> MedarbejderListe = null;
		ResultSet rs = null;
		try {
			MedarbejderListe = new ArrayList<MedarbejderDTO>();
			rs = getMedarbejderListStmt.executeQuery();

			while (rs.next()) {
				MedarbejderListe.add(new MedarbejderDTO(rs.getString("navn"), rs.getString("brugernavn"),
						rs.getString("adgangskode"), rs.getString("email"), rs.getString("afdeling"),
						rs.getString("postnr"), rs.getString("vejnavn"), rs.getString("husnr"), rs.getString("etage"),
						rs.getString("doer")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getMedarbejderList fejlede" + e);
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return MedarbejderListe;
	}

	@Override
	public int getMedarbejderSum() throws DALException {
		ResultSet rs = null;
		int antal = 0;
		try {
			rs = getMedarbejderSumStmt.executeQuery();

			if (rs.first())
				antal = rs.getInt("antal");
		} catch (SQLException e) {
			throw new DALException("Kaldet getMedarbejderSum fejlede " + e);
		} finally {
			try {
				rs.close();

			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return antal;
	}

	@Override
	public void createMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		try {
			// Argumenter inds�ttes i statement
			createMedarbejderStmt.setString(1, medarbejder.getBrugernavn());
			createMedarbejderStmt.setString(2, medarbejder.getNavn());
			createMedarbejderStmt.setString(3, medarbejder.getEmail());
			createMedarbejderStmt.setString(4, medarbejder.getAdgangskode());
			createMedarbejderStmt.setString(5, medarbejder.getAfdeling());
			createMedarbejderStmt.setString(6, medarbejder.getPostnr());
			createMedarbejderStmt.setString(7, medarbejder.getVejnavn());
			createMedarbejderStmt.setString(8, medarbejder.getHusnr());
			createMedarbejderStmt.setString(9, medarbejder.getEtage());
			createMedarbejderStmt.setString(10, medarbejder.getDoer());

			// Kald til databasen
			createMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createMedarbejder fejlede: " + e);
		}
	}

	@Override
	public void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		try {
			// Argumenter inds�ttes i statement
			updateMedarbejderStmt.setString(1, medarbejder.getNavn());
			updateMedarbejderStmt.setString(2, medarbejder.getEmail());
			updateMedarbejderStmt.setString(3, medarbejder.getNyAdgangskode());
			updateMedarbejderStmt.setString(4, medarbejder.getAfdeling());
			updateMedarbejderStmt.setString(5, medarbejder.getPostnr());
			updateMedarbejderStmt.setString(6, medarbejder.getVejnavn());
			updateMedarbejderStmt.setString(7, medarbejder.getHusnr());
			updateMedarbejderStmt.setString(8, medarbejder.getEtage());
			updateMedarbejderStmt.setString(9, medarbejder.getDoer());
			updateMedarbejderStmt.setString(10, medarbejder.getBrugernavn());

			// Kald til databasen
			updateMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateMedarbejder fejlede: " + e.getMessage());
		}

	}

	@Override
	public void deleteMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		try {
			// MedarbejderID inds�ttes i statement
			deleteMedarbejderStmt.setString(1, medarbejder.getBrugernavn());

			// Kald til databasen
			deleteMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet deleteMedarbejder fejlede");
		}

	}

	// close the database connection
	public void close() {
		try {
			Connector.conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void updateMedarbejderBruger(MedarbejderDTO medarbejder) throws DALException {
		ResultSet rs = null;
		try {
			getMedarbejderStmt.setString(1, medarbejder.getBrugernavn());
			rs = getMedarbejderStmt.executeQuery();
			if (rs.first()) {
				updateMedarbejderBrugerStmt.setString(1, medarbejder.getNavn());
				updateMedarbejderBrugerStmt.setString(2, medarbejder.getAdgangskode());
				updateMedarbejderBrugerStmt.setString(3, medarbejder.getEmail());
				updateMedarbejderBrugerStmt.setString(4, medarbejder.getBrugernavn());
				updateMedarbejderBrugerStmt.executeUpdate();
				return;
			}
			createMedarbejder(medarbejder);
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
}
