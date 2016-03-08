package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtu.rejseafregning.client.services.IMedarbejderDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.MedarbejderDTO;

public class MedarbejderDAO implements IMedarbejderDAO {

	private PreparedStatement getMedarbejderStmt = null;
	private PreparedStatement getMedarbejderListStmt = null;
	private PreparedStatement createMedarbejderStmt = null;
	private PreparedStatement updateMedarbejderStmt = null;
	private PreparedStatement deleteMedarbejderStmt = null;

	public MedarbejderDAO() throws Exception {

		// getMedarbejder statement
		getMedarbejderStmt = Connector.conn.prepareStatement("SELECT * FROM Medarbejder WHERE Medarbejder_ID = ?");

		// getMedarbejderList statement
		getMedarbejderListStmt = Connector.conn.prepareStatement("SELECT * FROM Medarbejder");

		// createMedarbejder statement
		createMedarbejderStmt = Connector.conn.prepareStatement("INSERT INTO Medarbejder VALUES(?, ?, ?, ?)");

		// updateMedarbejder statement
		updateMedarbejderStmt = Connector.conn.prepareStatement(
				"UPDATE Medarbejder SET Name = ?, Username = ?, Password = ?, Email = ? WHERE Medarbejder_ID = ?");

		// deleteMedarbejder statement
		deleteMedarbejderStmt = Connector.conn.prepareStatement("DELETE FROM Medarbejder WHERE Medarbejder_ID = ?");
	}

	@Override
	public MedarbejderDTO getMedarbejder(int medarbejderID) throws DALException {
		ResultSet rs = null;
		try {
			getMedarbejderStmt.setInt(1, medarbejderID);
			rs = getMedarbejderStmt.executeQuery();

			return new MedarbejderDTO(rs.getInt("medarbejder_ID"), rs.getString("Name"), rs.getString("Username"),
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
				MedarbejderListe.add(new MedarbejderDTO(rs.getInt("Medarbejder_ID"), rs.getString("Name"),
						rs.getString("Username"), rs.getString("Password"), rs.getString("Email")));
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
			updateMedarbejderStmt.setString(2, medarbejder.getBrugernavn());
			updateMedarbejderStmt.setString(3, medarbejder.getAdgangskode());
			updateMedarbejderStmt.setString(4, medarbejder.getEmail());
			updateMedarbejderStmt.setInt(5, medarbejder.getID());

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
			deleteMedarbejderStmt.setInt(1, medarbejder.getID());

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
