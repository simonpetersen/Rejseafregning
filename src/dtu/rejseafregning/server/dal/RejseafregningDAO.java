package dtu.rejseafregning.server.dal;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.RejseafregningDTO;

public class RejseafregningDAO extends RemoteServiceServlet implements IRejseafregningDAO {

	private PreparedStatement getRejseafregningStmt = null;
	private PreparedStatement getRejseafregningListStmt = null;
	private PreparedStatement getRejseafregningListNavnStmt = null;
	private PreparedStatement getRejseafregningListStatStmt = null;
	private PreparedStatement getRejseafregningListNavnStatStmt = null;
	private PreparedStatement getRejseafregningUdkastListStmt = null;
	private PreparedStatement getRejseafregningCirkulationListStmt = null;
	private PreparedStatement getRejseafregningAfsluttedeListStmt = null;
	private PreparedStatement createRejseafregningStmt = null;
	private PreparedStatement updateRejseafregningStmt = null;
	private PreparedStatement deleteRejseafregningStmt = null;

	public RejseafregningDAO() throws Exception {

		new Connector();

		// getRejseafregning statement
		getRejseafregningStmt = Connector.conn
				.prepareStatement("SELECT * FROM Rejseafregning WHERE Rejseafregning_ID = ?");

		// getRejseafregningList statement
		getRejseafregningListStmt = Connector.conn
				.prepareStatement("SELECT * FROM Rejseafregning WHERE brugernavn = ?");

		// getRejseafregningNavnList statement
		getRejseafregningListNavnStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_id, navn, rejsedag_id, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "sum, rejseafregning.anviser, rejseafregning.godkender "
				+ "FROM medarbejder, Rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND rejseafregning.brugernavn = ?");

		// getRejseafregningStatList statement
		getRejseafregningListStatStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_id, navn, rejsedag_id, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "sum, rejseafregning.anviser, rejseafregning.godkender "
				+ "FROM medarbejder, Rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND status = ?");

		// getRejseafregningNavnStatList statement
		getRejseafregningListNavnStatStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_id, navn, rejsedag_id, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "sum, rejseafregning.anviser, rejseafregning.godkender "
				+ "FROM medarbejder, Rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND rejseafregning.brugernavn = ? AND status = ?");
		
		// getRejseafregningsUdkastList statement
		getRejseafregningUdkastListStmt = Connector.conn
				.prepareStatement("SELECT * FROM Rejseafregning WHERE brugernavn = ? AND status = 'Udkast'");

		// getRejseafregningsCirkulationList statement
		getRejseafregningCirkulationListStmt = Connector.conn
				.prepareStatement("SELECT * FROM Rejseafregning WHERE brugernavn = ? "
						+ "AND status != 'Udkast' AND status != 'Overført til Oracle'");

		getRejseafregningAfsluttedeListStmt = Connector.conn.prepareStatement(
				"SELECT * FROM Rejseafregning WHERE brugernavn = ? " + "AND status = 'Overført til Oracle'");

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
			// Argument inds�ttes i stmt
			getRejseafregningStmt.setInt(1, rejseafregningID);
			// rs s�ttes fra databasen
			rs = getRejseafregningStmt.executeQuery();
			if (rs.next())
				return new RejseafregningDTO(rs.getInt("Rejseafregning_ID"), rs.getString("Medarbejdernavn"),
						rs.getString("Godkendernavn"), rs.getString("Anvisernavn"), rs.getString("Land"),
						rs.getString("By"), rs.getString("Anledning"), rs.getString("Status"), rs.getDate("Startdato"),
						rs.getDate("Slutdato"), rs.getInt("Sum"));
			throw new DALException("Rejseafregning findes ikke!");
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejseafregning fejlede");
		}
	}

	@Override
	public List<RejseafregningDTO> getRejseafregningList(String navn) throws DALException {
		List<RejseafregningDTO> RejseafregningListe = null;
		ResultSet rs = null;
		try {
			RejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningListStmt.setString(1, navn);
			rs = getRejseafregningListStmt.executeQuery();

			while (rs.next()) {
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
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
			// Argumenter ins�ttes
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

	@Override
	public List<RejseafregningDTO> getRejseafregningUdkastList(String navn) throws DALException {
		List<RejseafregningDTO> rejseafregningListe = null;
		ResultSet rs = null;
		try {
			rejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningUdkastListStmt.setString(1, navn);
			rs = getRejseafregningUdkastListStmt.executeQuery();

			while (rs.next()) {
				rejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
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
		return rejseafregningListe;
	}

	@Override
	public List<RejseafregningDTO> getRejseafregningCirkulationList(String navn) throws DALException {
		List<RejseafregningDTO> rejseafregningListe = null;
		ResultSet rs = null;
		try {
			rejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningCirkulationListStmt.setString(1, navn);
			rs = getRejseafregningCirkulationListStmt.executeQuery();

			while (rs.next()) {
				rejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejseafregningCirkulationList fejlede");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return rejseafregningListe;
	}

	@Override
	public List<RejseafregningDTO> getRejseafregningAfsluttedeList(String navn) throws DALException {
		List<RejseafregningDTO> rejseafregningListe = null;
		ResultSet rs = null;
		try {
			rejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningAfsluttedeListStmt.setString(1, navn);
			rs = getRejseafregningAfsluttedeListStmt.executeQuery();

			while (rs.next()) {
				rejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
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
		return rejseafregningListe;
	}

	@Override
	public List<RejseafregningDTO> getRejseafregningListNavn(String navn) throws DALException {
		List<RejseafregningDTO> RejseafregningListe = null;
		ResultSet rs = null;
		try {
			RejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningListNavnStmt.setString(1, navn);
			rs = getRejseafregningListNavnStmt.executeQuery();

			while (rs.next()) {
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("navn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
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
	public List<RejseafregningDTO> getRejseafregningListStat(String status) throws DALException {
		List<RejseafregningDTO> RejseafregningListe = null;
		ResultSet rs = null;
		try {
			RejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningListStatStmt.setString(1, status);
			rs = getRejseafregningListStatStmt.executeQuery();

			while (rs.next()) {
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("navn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
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
	public List<RejseafregningDTO> getRejseafregningListNavnStat(String navn, String status) throws DALException {
		List<RejseafregningDTO> RejseafregningListe = null;
		ResultSet rs = null;
		try {
			RejseafregningListe = new ArrayList<RejseafregningDTO>();
			getRejseafregningListNavnStatStmt.setString(1, navn);
			getRejseafregningListNavnStatStmt.setString(2, status);
			rs = getRejseafregningListStatStmt.executeQuery();

			while (rs.next()) {
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("Rejseafregning_ID"),
						rs.getString("navn"), rs.getString("Godkender"), rs.getString("Anviser"),
						rs.getString("Land"), rs.getString("City"), rs.getString("Anledning"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getInt("Sum")));
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
}
