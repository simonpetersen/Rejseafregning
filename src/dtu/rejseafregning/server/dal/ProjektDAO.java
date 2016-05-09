package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IProjektDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.ProjektDTO;

public class ProjektDAO extends RemoteServiceServlet implements IProjektDAO {

	private PreparedStatement getProjektStmt = null;
	private PreparedStatement getProjektListStmt = null;
	private PreparedStatement createProjektStmt = null;
	private PreparedStatement updateProjektStmt = null;
	private PreparedStatement deleteProjektStmt = null;

	public ProjektDAO() throws Exception {

		// getProjekt statement
		getProjektStmt = Connector.conn.prepareStatement("SELECT * FROM projekt WHERE nameProjekt = ?");

		// getProjektList statement
		getProjektListStmt = Connector.conn.prepareStatement("SELECT * FROM projekt");

		// createProjekt statement
		createProjektStmt = Connector.conn.prepareStatement("INSERT INTO projekt VALUES ( ?, ?)");

		// updateProjekt statement
		updateProjektStmt = Connector.conn
				.prepareStatement("UPDATE Projekt SET nameOp = ? WHERE nameProjekt = ?");

		// deleteProjekt statement
		deleteProjektStmt = Connector.conn.prepareStatement("DELETE FROM Projekt WHERE nameProjekt = ?");
	}

	@Override
	public ProjektDTO getProjekt(String Navn) throws DALException {
		ResultSet rs = null;
		try {
			getProjektStmt.setString(1, Navn);
			rs = getProjektStmt.executeQuery();
			if (rs.next())
				return new ProjektDTO(rs.getString("nameProjekt"), rs.getString("nameOp"));
			throw new DALException("Projekt findes ikke!");
		} catch (SQLException e) {
			throw new DALException("Kaldet getProjekt fejlede");
		}
	}

	@Override
	public List<ProjektDTO> getProjektList() throws DALException {
		List<ProjektDTO> ProjektListe = null;
		ResultSet rs = null;
		try {
			ProjektListe = new ArrayList<ProjektDTO>();
			rs = getProjektListStmt.executeQuery();

			while (rs.next()) {
				ProjektListe
						.add(new ProjektDTO(rs.getString("nameProjekt"), rs.getString("nameOp")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getProjektList fejlede");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return ProjektListe;
	}

	@Override
	public void createProjekt(ProjektDTO projekt) throws DALException {
		try {
			// Argumenter til statement
			createProjektStmt.setString(1, projekt.getProjektNavn());
			createProjektStmt.setString(2, projekt.getOpgaveNavn());

			// Kald til database
			createProjektStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createProjekt fejlede");
		}
	}

	@Override
	public void updateProjekt(ProjektDTO projekt) throws DALException {
		try {
			// Argumenter til statement
			updateProjektStmt.setString(1, projekt.getProjektNavn());
			updateProjektStmt.setString(2, projekt.getOpgaveNavn());

			// Kald til database
			updateProjektStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateProjekt fejlede");
		}

	}

	@Override
	public void deleteProjekt(ProjektDTO projekt) throws DALException {
		try {
			// Argumenter til statement
			deleteProjektStmt.setString(1, projekt.getProjektNavn());

			// Kald til database
			deleteProjektStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet deleteProjekt fejlede");
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
