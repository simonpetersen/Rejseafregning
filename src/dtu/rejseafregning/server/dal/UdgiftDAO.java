package dtu.rejseafregning.server.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IUdgiftDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.UdgiftDTO;

public class UdgiftDAO extends RemoteServiceServlet implements IUdgiftDAO {

	private PreparedStatement getUdgiftStmt = null;
	private PreparedStatement getUdgiftListStmt = null;
	private PreparedStatement createUdgiftStmt = null;
	private PreparedStatement updateUdgiftStmt = null;
	private PreparedStatement deleteUdgiftStmt = null;

	public UdgiftDAO() throws Exception {
		// getUdgift statement
		getUdgiftStmt = Connector.conn.prepareStatement("SELECT * FROM Udgift WHERE Udgift_ID = ?");

		// getUdgiftList statement
		getUdgiftListStmt = Connector.conn.prepareStatement("SELECT * FROM Udgift");

		// createUdgift statement
		createUdgiftStmt = Connector.conn.prepareStatement("INSERT INTO Udgift (rejseafregning_ID, bilag_ID, udgiftType, betalingsType, "
				+ "forklaring, dato, beloeb) VALUES(?, ?, ?, ?, ?, ?, ?)");

		// updateUdgift statement
		updateUdgiftStmt = Connector.conn.prepareStatement(
				"UPDATE Udgift SET Bilag_ID = ?, Udgifttype = ?, Betalingtype = ?, Forklaring = ?, Dato = ? WHERE Udgift_ID = ?");

		// deleteUdgift statement
		deleteUdgiftStmt = Connector.conn.prepareStatement("DELETE FROM Udgift WHERE Udgift_ID = ?");
	}

	@Override
	public UdgiftDTO getUdgift(int udgiftID) throws DALException {
		ResultSet rs = null;
		try {
			// Argument inds�ttes i statement
			getUdgiftStmt.setInt(1, udgiftID);
			// rs lig kald fra database
			rs = getUdgiftStmt.executeQuery();
			if (rs.next())
				return new UdgiftDTO(rs.getInt("Udgift_ID"), rs.getInt("rejseafregning_ID"), rs.getInt("Bilag_ID"), rs.getString("Udgifttype"),
					rs.getString("Betalingtype"), rs.getString("forklaring"), rs.getDate("Dato"), rs.getDouble("beloeb"));
			throw new DALException("Udgift findes ikke!");
		} catch (SQLException e) {
			throw new DALException("Kaldet getUdgift fejlede");
		}
	}

	@Override
	public List<UdgiftDTO> getUdgiftList() throws DALException {
		List<UdgiftDTO> UdgiftListe = null;
		ResultSet rs = null;
		try {
			UdgiftListe = new ArrayList<UdgiftDTO>();

			rs = getUdgiftListStmt.executeQuery();

			while (rs.next()) {
				UdgiftListe.add(new UdgiftDTO(rs.getInt("Udgift_ID"), rs.getInt("rejseafregning_ID"), rs.getInt("Bilag_ID"), rs.getString("Udgifttype"),
						rs.getString("Betalingtype"), rs.getString("forklaring"), rs.getDate("Dato"), rs.getDouble("beloeb")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getUdgiftList fejlede");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return null;
	}

	@Override
	public void createUdgift(UdgiftDTO udgift) throws DALException {
		try {
			// Argumenter til statement
			createUdgiftStmt.setInt(1, udgift.getRejseafregningID());
			createUdgiftStmt.setInt(2, udgift.getBilagID());
			createUdgiftStmt.setString(3, udgift.getUdgiftType());
			createUdgiftStmt.setString(4, udgift.getBetalingType());
			createUdgiftStmt.setString(5, udgift.getForklaring());
			createUdgiftStmt.setDate(6, (Date) udgift.getDato());
			createUdgiftStmt.setDouble(7, udgift.getBeloeb());

			// Kald til database
			createUdgiftStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet createUdgift fejlede");
		}
	}

	@Override
	public void updateUdgift(UdgiftDTO udgift) throws DALException {
		try {

			// Argumenter til statement
			updateUdgiftStmt.setInt(1, udgift.getBilagID());
			updateUdgiftStmt.setString(2, udgift.getUdgiftType());
			updateUdgiftStmt.setString(3, udgift.getBetalingType());
			updateUdgiftStmt.setString(4, udgift.getForklaring());
			updateUdgiftStmt.setDate(5, (Date) udgift.getDato());
			updateUdgiftStmt.setInt(6, udgift.getUdgiftID());

			// Kald til database
			updateUdgiftStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateUdgift fejlede");
		}
	}

	@Override
	public void deleteUdgift(UdgiftDTO udgift) throws DALException {
		try {
			// Argument til statement
			deleteUdgiftStmt.setInt(1, udgift.getUdgiftID());

			// Kald til database
			deleteUdgiftStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet deleteUdgift fejlede");
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
