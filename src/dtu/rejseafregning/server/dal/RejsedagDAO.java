package dtu.rejseafregning.server.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IRejsedagDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejsedagDTO;

public class RejsedagDAO extends RemoteServiceServlet implements IRejsedagDAO {

	private PreparedStatement getRejsedagStmt = null;
	private PreparedStatement getRejsedagListStmt = null;
	private PreparedStatement createRejsedagStmt = null;
	private PreparedStatement updateRejsedagStmt = null;
	private PreparedStatement deleteRejsedagStmt = null;

	public RejsedagDAO() throws Exception {

		// getRejsedag statement
		getRejsedagStmt = Connector.conn.prepareStatement("SELECT * FROM rejsedag WHERE rejsedag_ID = ?");

		// getRejsedagList statement
		getRejsedagListStmt = Connector.conn.prepareStatement("SELECT * FROM rejsedag");

		// createRejsedag statement
		createRejsedagStmt = Connector.conn.prepareStatement("INSERT INTO rejsedag (rejseafregning_ID, dato, start, slut, morgenmad, frokost, "
				+ "aftendsmad) VALUES (?, ?, ?, ?, ?, ?, ?)");

		// updateRejsedag statement
		updateRejsedagStmt = Connector.conn.prepareStatement(
				"UPDATE rejsedag SET morgenmad = ?, frokost = ?, aftensmad = ?, start = ?, slut = ? WHERE rejsedag_ID = ?");

		// deleteRejsedag statement
		deleteRejsedagStmt = Connector.conn.prepareStatement("DELETE FROM rejsedag WHERE rejsedag_ID = ?");
	}

	@Override
	public RejsedagDTO getRejsedag(int rejsedagID) throws DALException {
		ResultSet rs = null;
		try {
			getRejsedagStmt.setInt(1, rejsedagID);

			rs = getRejsedagStmt.executeQuery();
			if (rs.next())
				return new RejsedagDTO(rs.getInt("rejsedag_ID"), rs.getInt("rejseafregning_ID"), rs.getBoolean("morgenmad"), rs.getBoolean("frokost"),
					rs.getBoolean("aftensmad"), rs.getTime("start"), rs.getTime("slut"), rs.getDate("dato"));
			throw new DALException("Rejsedag findes ikke!");
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
				RejsedagListe.add(new RejsedagDTO(rs.getInt("rejsedag_ID"), rs.getInt("rejseafregning_ID"), rs.getBoolean("morgenmad"),
						rs.getBoolean("frokost"), rs.getBoolean("aftensmad"), rs.getTime("start"), rs.getTime("slut"), rs.getDate("dato")));
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
			createRejsedagStmt.setInt(1, rejsedag.getRejseafregningID());
			createRejsedagStmt.setDate(2, (Date) rejsedag.getDato());
			createRejsedagStmt.setTime(3, rejsedag.getStart());
			createRejsedagStmt.setTime(4, rejsedag.getSlut());
			createRejsedagStmt.setBoolean(5, rejsedag.harMorgenmad());
			createRejsedagStmt.setBoolean(6, rejsedag.harFrokost());
			createRejsedagStmt.setBoolean(7, rejsedag.harAftensmad());
			
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
			updateRejsedagStmt.setTime(4, rejsedag.getStart());
			updateRejsedagStmt.setTime(5, rejsedag.getSlut());
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
