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

	private PreparedStatement getMedarbejderStmt = null;
	private PreparedStatement getMedarbejderListStmt = null;
	private PreparedStatement createMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderStmt = null;
	private PreparedStatement deleteMedarbejderStmt = null;

	public MedarbejderDAO() throws Exception {

		// getMedarbejder statement
		getMedarbejderStmt = Connector.conn.prepareStatement("SELECT * FROM Medarbejder WHERE Brugernavn = '?'");

		// getMedarbejderList statement
		getMedarbejderListStmt = Connector.conn.prepareStatement("SELECT * FROM Medarbejder");

		// createMedarbejder statement
		createMedarbejderStmt = Connector.conn.prepareStatement("INSERT INTO Medarbejder VALUES(?, ?, ?, ?)");

		// updateMedarbejder statement
		updateMedarbejderStmt = Connector.conn.prepareStatement(
				"UPDATE Medarbejder SET Navn = ?, Password = ?, Email = ? WHERE Brugernavn = ?");

		// deleteMedarbejder statement
		deleteMedarbejderStmt = Connector.conn.prepareStatement("DELETE FROM Medarbejder WHERE Brugernavn = ?");
	}

	@Override
	public MedarbejderDTO getMedarbejder(String Brugernavn) throws DALException {
		ResultSet rs = null;
		try {
			getMedarbejderStmt.setString(1, Brugernavn);
			rs = getMedarbejderStmt.executeQuery();

			return new MedarbejderDTO(rs.getString("Navn"), rs.getString("Brugernavn"),
					rs.getString("Password"), rs.getString("Email"));
		} catch (SQLException e) {
			throw new DALException("Kaldet getMedarbejder fejlede");
		}
	}

	@Override
	public List<MedarbejderDTO> getMedarbejderList() throws DALException {
		List<MedarbejderDTO> MedarbejderListe = null;
		ResultSet rs = null;
		try {
			rs = getMedarbejderListStmt.executeQuery();
			MedarbejderListe = new ArrayList<MedarbejderDTO>();

			while (rs.next()) {
				MedarbejderListe.add(new MedarbejderDTO(rs.getString("Navn"),
						rs.getString("Brugernavn"), rs.getString("Password"), rs.getString("Email")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getMedarbejderList fejlede");
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
			createMedarbejderStmt.setString(1, medarbejder.getNavn());
			createMedarbejderStmt.setString(2, medarbejder.getBrugernavn());
			createMedarbejderStmt.setString(3, medarbejder.getAdgangskode());
			createMedarbejderStmt.setString(4, medarbejder.getEmail());

			//Kald til databasen
			createMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createMedarbejder fejlede");
		}
	}

	@Override
	public void updateMedarbejder(MedarbejderDTO medarbejder) throws DALException {
		try {
			// Argumenter inds�ttes i statement
			updateMedarbejderStmt.setString(1, medarbejder.getNavn());
			updateMedarbejderStmt.setString(2, medarbejder.getAdgangskode());
			updateMedarbejderStmt.setString(3, medarbejder.getEmail());
			updateMedarbejderStmt.setString(4, medarbejder.getBrugernavn());

			// Kald til databasen
			updateMedarbejderStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateMedarbejder fejlede");
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
}
