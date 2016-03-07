package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.OpgaveDTO;

public class OpgaveDAO implements IOpgaveDAO {

	private PreparedStatement getOpgaveStmt = null;
	private PreparedStatement getOpgaveListStmt = null;
	private PreparedStatement createOpgaveStmt = null;
	private PreparedStatement updateOpgaveStmt = null;
	private PreparedStatement deleteOpgaveStmt = null;

	public OpgaveDAO() throws Exception {

		// getOpgave statement
		getOpgaveStmt = Connector.conn.prepareStatement("SELECT * FROM Opgave WHERE Opgave_ID = ?");

		// getOpgaveList statement
		getOpgaveListStmt = Connector.conn.prepareStatement("SELECT * FROM Opgave");

		// createOpgave statement
		createOpgaveStmt = Connector.conn.prepareStatement("INSERT INTO Opgave VALUES (?, ?)");

		// updateOpgave statement
		updateOpgaveStmt = Connector.conn.prepareStatement("UPDATE Opgave SET Opgavenavn = ? WHERE Opgave_ID = ?");

		// deleteOpgave statement
		deleteOpgaveStmt = Connector.conn.prepareStatement("DELETE FROM Opgave WHERE Opgave_ID = ?");
	}

	@Override
	public OpgaveDTO getOpgave(int opgaveID) throws DALException {
		ResultSet rs = null;
		try {
			getOpgaveStmt.setInt(1, opgaveID);
			rs = getOpgaveStmt.executeQuery();

			return new OpgaveDTO(rs.getInt("Opgave_ID"), rs.getString("Navn"));
		} catch (SQLException e) {
			throw new DALException("Kaldet getOpgave fejlede");
		}
	}

	@Override
	public List<OpgaveDTO> getOpgaveList() throws DALException {
		List<OpgaveDTO> OpgaveListe = null;
		ResultSet rs = null;
		try {
			rs = getOpgaveListStmt.executeQuery();
			OpgaveListe = new ArrayList<OpgaveDTO>();

			while (rs.next()) {
				OpgaveListe.add(new OpgaveDTO(rs.getInt("Opgave_ID"), rs.getString("Navn")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getOpgaveList fejlede");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return OpgaveListe;
	}

	@Override
	public void createOpgave(OpgaveDTO opgave) throws DALException {
		try {
			// Argumenter til statement
			createOpgaveStmt.setInt(1, opgave.getOpgaveID());
			createOpgaveStmt.setString(2, opgave.getOpgaveNavn());

			// Kald til databasen
			createOpgaveStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createOpgave fejlede");
		}
	}

	@Override
	public void updateOpgave(OpgaveDTO opgave) throws DALException {
		try {
			// Argumenter til statement
			updateOpgaveStmt.setString(1, opgave.getOpgaveNavn());
			updateOpgaveStmt.setInt(2, opgave.getOpgaveID());

			// Kald til database
			updateOpgaveStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateOpgave fejlede");
		}
	}

	@Override
	public void deleteOpgave(OpgaveDTO opgave) throws DALException {
		try {
			// Argumenter til statement
			deleteOpgaveStmt.setInt(1, opgave.getOpgaveID());

			// Kald til database
			deleteOpgaveStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet deleteOpgave fejlede");
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
