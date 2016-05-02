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
	private PreparedStatement createMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderBrugerStmt = null;
	private PreparedStatement deleteMedarbejderStmt = null;

	public MedarbejderDAO() throws Exception {
		
		new Connector();

		// getMedarbejder statement
		getMedarbejderStmt = Connector.conn.prepareStatement("SELECT * FROM Medarbejder WHERE Brugernavn = ?");

		// getMedarbejderList statement
		getMedarbejderListStmt = Connector.conn.prepareStatement("SELECT * FROM Medarbejder");

		// createMedarbejder statement
		createMedarbejderStmt = Connector.conn.prepareStatement("INSERT INTO Medarbejder (brugernavn, navn, email, adgangskode, afdeling) VALUES(?, ?, ?, ?, ?)");

		// updateMedarbejder statement
		updateMedarbejderStmt = Connector.conn.prepareStatement(
				"UPDATE Medarbejder SET Navn = ?, Adgangskode = ?, Email = ?, Postnr = ?, Vejnavn = ?, Husnr = ?, Etage = ?, Doer = ? "
				+ "WHERE Brugernavn = ?");
		
		updateMedarbejderBrugerStmt = Connector.conn.prepareStatement(
				"UPDATE Medarbejder SET Navn = ?, Adgangskode = ?, Email = ? WHERE Brugernavn = ?");

		// deleteMedarbejder statement
		deleteMedarbejderStmt = Connector.conn.prepareStatement("DELETE FROM Medarbejder WHERE Brugernavn = ?");
	}
	
	@Override
	public MedarbejderDTO login(String brugernavn, String adgangskode) throws DALException {
		MedarbejderDTO bruger = getMedarbejder(brugernavn);
		if (!bruger.getAdgangskode().equals(adgangskode)) throw new DALException("Forkert adgangskode");
		return bruger;
	}

	@Override
	public MedarbejderDTO getMedarbejder(String Brugernavn) throws DALException {
		ResultSet rs = null;
		try {
			getMedarbejderStmt.setString(1, Brugernavn);
			rs = getMedarbejderStmt.executeQuery();
			if (rs.first()) {
				return new MedarbejderDTO(rs.getString("Navn"), rs.getString("Brugernavn"),
					rs.getString("adgangskode"), rs.getString("Email"), rs.getString("Afdeling"), true, rs.getString("postnr"), 
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
				MedarbejderListe.add(new MedarbejderDTO(rs.getString("Navn"), rs.getString("Brugernavn"),
						rs.getString("adgangskode"), rs.getString("Email"), rs.getString("Afdeling"), true, rs.getString("postnr"), 
						rs.getString("vejnavn"), rs.getString("husnr"), rs.getString("etage"), rs.getString("doer")));
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
	public void createMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		try {
			//Argumenter inds�ttes i statement
			createMedarbejderStmt.setString(1, medarbejder.getBrugernavn());
			createMedarbejderStmt.setString(2, medarbejder.getNavn());
			createMedarbejderStmt.setString(3, medarbejder.getEmail());
			createMedarbejderStmt.setString(4, medarbejder.getAdgangskode());
			createMedarbejderStmt.setString(5, medarbejder.getAfdeling());

			//Kald til databasen
			createMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createMedarbejder fejlede: "+ e);
		}
	}

	@Override
	public void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		try {
			// Argumenter inds�ttes i statement
			updateMedarbejderStmt.setString(1, medarbejder.getNavn());
			updateMedarbejderStmt.setString(2, medarbejder.getNyAdgangskode());
			updateMedarbejderStmt.setString(3, medarbejder.getEmail());
			updateMedarbejderStmt.setString(4, medarbejder.getPostnr());
			updateMedarbejderStmt.setString(5, medarbejder.getVejnavn());
			updateMedarbejderStmt.setString(6, medarbejder.getHusnr());
			updateMedarbejderStmt.setString(7, medarbejder.getEtage());
			updateMedarbejderStmt.setString(8, medarbejder.getDoer());
			updateMedarbejderStmt.setString(9, medarbejder.getBrugernavn());

			// Kald til databasen
			updateMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateMedarbejder fejlede: "+e.getMessage());
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
