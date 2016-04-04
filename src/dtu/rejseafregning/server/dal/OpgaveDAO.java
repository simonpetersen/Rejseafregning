package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IOpgaveDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.OpgaveDTO;

public class OpgaveDAO extends RemoteServiceServlet implements IOpgaveDAO {

	private PreparedStatement getOpgaveStmt = null;
	private PreparedStatement getOpgaveListStmt = null;
	private PreparedStatement createOpgaveStmt = null;
	private PreparedStatement updateOpgaveStmt = null;
	private PreparedStatement deleteOpgaveStmt = null;

	public OpgaveDAO() throws Exception {
		new Connector();
		
		// getOpgave statement
		getOpgaveStmt = Connector.conn.prepareStatement("SELECT * FROM Opgave WHERE Navn = ?");

		// getOpgaveList statement
		getOpgaveListStmt = Connector.conn.prepareStatement("SELECT * FROM Opgave");

		// createOpgave statement
		createOpgaveStmt = Connector.conn.prepareStatement("INSERT INTO Opgave VALUES (?)");

		// updateOpgave statement
		updateOpgaveStmt = Connector.conn.prepareStatement("UPDATE Opgave SET Navn = ? WHERE Navn = ?");

		// deleteOpgave statement
		deleteOpgaveStmt = Connector.conn.prepareStatement("DELETE FROM Opgave WHERE Navn = ?");
	}

	@Override
	public OpgaveDTO getOpgave(String opgaveNavn) throws DALException {
		ResultSet rs = null;
		try {
			getOpgaveStmt.setString(1, opgaveNavn);
			rs = getOpgaveStmt.executeQuery();
			if (rs.next())
				return new OpgaveDTO(rs.getString("Navn"));
			throw new DALException("Opgave findes ikke!");
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
				OpgaveListe.add(new OpgaveDTO(rs.getString("Navn")));
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
			createOpgaveStmt.setString(1, opgave.getOpgaveNavn());

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
			deleteOpgaveStmt.setString(1, opgave.getOpgaveNavn());

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
