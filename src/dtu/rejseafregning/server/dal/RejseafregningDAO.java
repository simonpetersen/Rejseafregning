package dtu.rejseafregning.server.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class RejseafregningDAO implements IRejseafregningDAO {

	private PreparedStatement getRejseafregningStmt = null;
	private PreparedStatement getRejseafregningListStmt = null;
	private PreparedStatement createRejseafregningStmt = null;
	private PreparedStatement updateRejseafregningStmt = null;
	private PreparedStatement deleteRejseafregningStmt = null;

	public RejseafregningDAO() throws Exception {
		// getRejseafregning statement
		getRejseafregningStmt = Connector.conn
				.prepareStatement("SELECT * FROM Rejseafregning WHERE Rejseafregning_ID = ?");

		// getRejseafregningList statement
		getRejseafregningListStmt = Connector.conn.prepareStatement("SELECT * FROM Rejseafregning");

		// createRejseafregning statement
		createRejseafregningStmt = Connector.conn
				.prepareStatement("INSERT INTO Rejseafregning VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

		// updateRejseafregning statement
		updateRejseafregningStmt = Connector.conn.prepareStatement(
				"UPDATE Rejseafregning SET Medarbejdernavn = ?, Godkendernavn = ?, Anvisernavn = ?, Land = ?, By = ?, Startdato = ?, Slutdato = ? WHERE Rejseafregning_ID = ?");

		// deleteRejseafregning statement
		deleteRejseafregningStmt = Connector.conn
				.prepareStatement("DELETE FROM Rejseafregning WHERE Rejseafregning_ID = ?");
	}

	@Override
	public RejseafregningDTO getRejseafregning(int rejseafregningID) throws DALException {
		ResultSet rs = null;
		try {
			// Argument indsættes i stmt
			getRejseafregningStmt.setInt(1, rejseafregningID);
			// rs sættes fra databasen
			rs = getRejseafregningStmt.executeQuery();

			return new RejseafregningDTO(rs.getInt("Rejseafregning_ID"), rs.getString("Medarbejdernavn"),
					rs.getString("Godkendernavn"), rs.getString("Anvisernavn"), rs.getString("Land"),
					rs.getString("By"), rs.getDate("Startdato"), rs.getDate("Slutdato"));
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejseafregning fejlede");
		}
	}

	@Override
	public List<RejseafregningDTO> getRejseafregningList() throws DALException {
		List<RejseafregningDTO> RejseafregningListe = null;
		ResultSet rs = null;
		try {
			RejseafregningListe = new ArrayList<RejseafregningDTO>();
			rs = getRejseafregningStmt.executeQuery();

			while (rs.next()) {
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("Medarbejdernavn"), rs.getString("Godkendernavn"), rs.getString("Anvisernavn"),
						rs.getString("Land"), rs.getString("By"), rs.getDate("Startdato"), rs.getDate("Slutdato")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejseafregningList fejlede");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return RejseafregningListe;
	}

	@Override
	public void createRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		try {
			// Argumenter insættes
			createRejseafregningStmt.setInt(1, rejseafregning.getRejseafregningID());
			createRejseafregningStmt.setString(2, rejseafregning.getMedarbejderNavn());
			createRejseafregningStmt.setString(3, rejseafregning.getGodkenderNavn());
			createRejseafregningStmt.setString(4, rejseafregning.getAnviserNavn());
			createRejseafregningStmt.setString(5, rejseafregning.getLand());
			createRejseafregningStmt.setString(6, rejseafregning.getBy());
			createRejseafregningStmt.setDate(7, (Date) rejseafregning.getStartDato());
			createRejseafregningStmt.setDate(8, (Date) rejseafregning.getSlutDato());

			// Kald til database
			createRejseafregningStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createRejseafregning fejlede");
		}
	}

	@Override
	public void updateRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		try {
			// Argumenter til statement
			updateRejseafregningStmt.setString(1, rejseafregning.getMedarbejderNavn());
			updateRejseafregningStmt.setString(2, rejseafregning.getGodkenderNavn());
			updateRejseafregningStmt.setString(3, rejseafregning.getAnviserNavn());
			updateRejseafregningStmt.setString(4, rejseafregning.getLand());
			updateRejseafregningStmt.setString(5, rejseafregning.getBy());
			updateRejseafregningStmt.setDate(6, (Date) rejseafregning.getStartDato());
			updateRejseafregningStmt.setDate(7, (Date) rejseafregning.getSlutDato());
			updateRejseafregningStmt.setInt(8, rejseafregning.getRejseafregningID());

			// Kald til database
			updateRejseafregningStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateRejseafregning fejlede");
		}
	}

	@Override
	public void deleteRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		try {
			// Argumenter til statement
			deleteRejseafregningStmt.setInt(1, rejseafregning.getRejseafregningID());

			// Kald til database
			deleteRejseafregningStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet deleteRejseafregning fejlede");
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
