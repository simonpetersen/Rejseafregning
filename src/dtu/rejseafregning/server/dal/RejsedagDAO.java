package dtu.rejseafregning.server.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dtu.rejseafregning.client.services.IRejsedagDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejsedagDTO;

public class RejsedagDAO implements IRejsedagDAO {

	private PreparedStatement getRejsedagStmt = null;
	private PreparedStatement getRejsedagListStmt = null;
	private PreparedStatement createRejsedagStmt = null;
	private PreparedStatement updateRejsedagStmt = null;
	private PreparedStatement deleteRejsedagStmt = null;

	public RejsedagDAO() throws Exception {

		// getRejsedag statement
		getRejsedagStmt = Connector.conn.prepareStatement("SELECT * FROM Rejsedag WHERE Rejsedag_ID = ?");

		// getRejsedagList statement
		getRejsedagListStmt = Connector.conn.prepareStatement("SELECT * FROM Rejsedag");

		// createRejsedag statement
		createRejsedagStmt = Connector.conn.prepareStatement("INSERT INTO Rejsedag VALUES (?, ?, ?, ?, ?, ?)");

		// updateRejsedag statement
		updateRejsedagStmt = Connector.conn.prepareStatement(
				"UPDATE Rejsedag SET Morgenmad = ?, Frokost = ?, Aftensmad = ?, Start = ?, Slut = ? WHERE Rejsedag_ID = ?");

		// deleteRejsedag statement
		deleteRejsedagStmt = Connector.conn.prepareStatement("DELETE FROM Rejsedag WHERE Rejsedag_ID = ?");
	}

	@Override
	public RejsedagDTO getRejsedag(int rejsedagID) throws DALException {
		ResultSet rs = null;
		try {
			getRejsedagStmt.setInt(1, rejsedagID);

			rs = getRejsedagStmt.executeQuery();

			return new RejsedagDTO(rs.getInt("Rejsedag_ID"), rs.getBoolean("Morgenmad"), rs.getBoolean("Frokost"),
					rs.getBoolean("Aftensmad"), rs.getDate("Start"), rs.getDate("Slut"));
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejsedag fejlede");
		}
	}

	@Override
	public List<RejsedagDTO> getRejsedagList() throws DALException {
		List<RejsedagDTO> RejsedagListe = null;
		ResultSet rs = null;
		try {
			RejsedagListe = new ArrayList<RejsedagDTO>();

			rs = getRejsedagListStmt.executeQuery();

			while (rs.next()) {
				RejsedagListe.add(new RejsedagDTO(rs.getInt("Rejsedag_ID"), rs.getBoolean("Morgenmad"),
						rs.getBoolean("Frokost"), rs.getBoolean("Aftensmad"), rs.getDate("Start"), rs.getDate("Slut")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejsedgList fejlede");
		}
		finally{
			try{
				rs.close();
			}
			catch(SQLException e){
				e.printStackTrace();
				close();
			}
		}
		return RejsedagListe;
	}

	@Override
	public void createRejsedag(RejsedagDTO rejsedag) throws DALException {
		try{
			//Argumenter til statement
			createRejsedagStmt.setInt(1, rejsedag.getRejsedagID());
			createRejsedagStmt.setBoolean(2, rejsedag.harMorgenmad());
			createRejsedagStmt.setBoolean(3, rejsedag.harFrokost());
			createRejsedagStmt.setBoolean(4, rejsedag.harAftensmad());
			createRejsedagStmt.setDate(5, (Date) rejsedag.getStart());
			createRejsedagStmt.setDate(6, (Date) rejsedag.getSlut());
			
			//Kald til database
			createRejsedagStmt.executeUpdate();
		}
		catch(SQLException e){
			throw new DALException("Kaldet createRejsedag fejlede");
		}
	}

	@Override
	public void updateRejsedag(RejsedagDTO rejsedag) throws DALException {
		try{
			//Argumenter til statement
			updateRejsedagStmt.setBoolean(1, rejsedag.harMorgenmad());
			updateRejsedagStmt.setBoolean(2, rejsedag.harFrokost());
			updateRejsedagStmt.setBoolean(3, rejsedag.harAftensmad());
			updateRejsedagStmt.setDate(4, (Date) rejsedag.getStart());
			updateRejsedagStmt.setDate(5, (Date) rejsedag.getSlut());
			updateRejsedagStmt.setInt(6, rejsedag.getRejsedagID());
			
			//Kald til database
			updateRejsedagStmt.executeUpdate();
		}
		catch(SQLException e){
			throw new DALException("Kaldet updateRejsedag fejlede");
		}

	}

	@Override
	public void deleteRejsedag(RejsedagDTO rejsedag) throws DALException {
		try{
			//Argumenter til statement
			deleteRejsedagStmt.setInt(1, rejsedag.getRejsedagID());
			
			//Kald til database
			deleteRejsedagStmt.executeUpdate();
		}
		catch(SQLException e){
			throw new DALException("Kaldet deleteRejsedag fejlede");
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
