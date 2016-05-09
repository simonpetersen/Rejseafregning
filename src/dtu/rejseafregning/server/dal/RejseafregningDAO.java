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
import dtu.rejseafregning.shared.GodkendelseJoinDTO;
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
	private PreparedStatement getRejseafregningAnvisningJoinListStmt = null;
	private PreparedStatement getRejseafregningGodkendelseJoinListStmt = null;
	private PreparedStatement createRejseafregningStmt = null;
	private PreparedStatement updateRejseafregningStmt = null;
	private PreparedStatement updateStatusStmt = null;
	private PreparedStatement deleteRejseafregningStmt = null;

	public RejseafregningDAO() throws Exception {

		new Connector();

		// getRejseafregning statement
		getRejseafregningStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE rejseafregning_ID = ?");

		// getRejseafregningList statement
		getRejseafregningListStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE brugernavn = ?");

		// getRejseafregningNavnList statement
		getRejseafregningListNavnStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_id, navn, rejsedag_id, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "sum, rejseafregning.anviser, rejseafregning.godkender "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND medarbejder.navn = ?");

		// getRejseafregningStatList statement
		getRejseafregningListStatStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_id, navn, rejsedag_id, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "sum, rejseafregning.anviser, rejseafregning.godkender "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND status = ?");

		// getRejseafregningNavnStatList statement
		getRejseafregningListNavnStatStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_id, navn, rejsedag_id, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "sum, rejseafregning.anviser, rejseafregning.godkender "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND rejseafregning.brugernavn = ? AND status = ?");
		
		// getRejseafregningAnvisningJoinList
		getRejseafregningAnvisningJoinListStmt = Connector.conn.prepareStatement("SELECT medarbejder.navn, rejseafregning.* "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND anviser = ? AND status = 'Til Anvisning'");
		
		// getRejseafregningAnvisningJoinList
		getRejseafregningGodkendelseJoinListStmt = Connector.conn.prepareStatement("SELECT medarbejder.navn, rejseafregning.* "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND godkender = ? AND status = 'Til Godkendelse'");
		
		// getRejseafregningsUdkastList statement
		getRejseafregningUdkastListStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE brugernavn = ? AND status = 'Udkast'");

		// getRejseafregningsCirkulationList statement
		getRejseafregningCirkulationListStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE brugernavn = ? "
						+ "AND status != 'Udkast' AND status != 'Overført til Oracle'");

		getRejseafregningAfsluttedeListStmt = Connector.conn.prepareStatement(
				"SELECT * FROM rejseafregning WHERE brugernavn = ? " + "AND status = 'Overført til Oracle'");

		// createRejseafregning statement
		createRejseafregningStmt = Connector.conn
				.prepareStatement("INSERT INTO rejseafregning (brugernavn, nameProjekt, land, status, datoStart, datoSlut, city, anledning, "
						+ "anviser, godkender, forklaring) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

		// updateRejseafregning statement
		updateRejseafregningStmt = Connector.conn.prepareStatement(
				"UPDATE rejseafregning SET brugernavn = ?, nameProjekt = ?, land = ?, status = ?, datoStart = ?, "
				+ "datoSlut = ?, by = ?, anledning = ?, anviser = ?, godkender = ?, forklaring = ? WHERE rejseafregning_ID = ?");

		updateStatusStmt = Connector.conn.prepareStatement("UPDATE rejseafregning SET status = ? WHERE rejseafregning_ID = ?");
		
		// deleteRejseafregning statement
		deleteRejseafregningStmt = Connector.conn
				.prepareStatement("DELETE FROM rejseafregning WHERE rejseafregning_ID = ?");
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
				return new RejseafregningDTO(rs.getInt("rejseafregning_ID"), rs.getString("medarbejdernavn"),
						rs.getString("godkendernavn"), rs.getString("anvisernavn"), rs.getString("land"),
						rs.getString("by"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"), rs.getDate("datoStart"),
						rs.getDate("datoSlut"), rs.getString("nameProjekt"));
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
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
			createRejseafregningStmt.setString(1, rejseafregning.getMedarbejderNavn());
			createRejseafregningStmt.setString(2, rejseafregning.getProjektNavn());
			createRejseafregningStmt.setString(3, rejseafregning.getLand());
			createRejseafregningStmt.setString(4, rejseafregning.getStatus());
			createRejseafregningStmt.setDate(5, (Date) rejseafregning.getStartDato());
			createRejseafregningStmt.setDate(6, (Date) rejseafregning.getSlutDato());
			createRejseafregningStmt.setString(7, rejseafregning.getBy());
			createRejseafregningStmt.setString(8, rejseafregning.getAnledning());
			createRejseafregningStmt.setString(9, rejseafregning.getAnviserNavn());
			createRejseafregningStmt.setString(10, rejseafregning.getGodkenderNavn());
			createRejseafregningStmt.setString(11, rejseafregning.getForklaring());

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
			updateRejseafregningStmt.setString(2, rejseafregning.getProjektNavn());
			updateRejseafregningStmt.setString(3, rejseafregning.getLand());
			updateRejseafregningStmt.setString(4, rejseafregning.getStatus());
			updateRejseafregningStmt.setDate(5, (Date) rejseafregning.getStartDato());
			updateRejseafregningStmt.setDate(6, (Date) rejseafregning.getSlutDato());
			updateRejseafregningStmt.setString(7, rejseafregning.getBy());
			updateRejseafregningStmt.setString(8, rejseafregning.getAnledning());
			updateRejseafregningStmt.setString(9, rejseafregning.getAnviserNavn());
			updateRejseafregningStmt.setString(10, rejseafregning.getGodkenderNavn());
			updateRejseafregningStmt.setString(11, rejseafregning.getForklaring());
			updateRejseafregningStmt.setInt(12, rejseafregning.getRejseafregningID());
			
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
				rejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
				rejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
				rejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("Status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("brugernavn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt")));
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
	public List<GodkendelseJoinDTO> getRejseafregningAnvisningList(String navn) throws DALException {
		List<GodkendelseJoinDTO> rejseafregningListe = null;
		ResultSet rs = null;
		try {
			rejseafregningListe = new ArrayList<GodkendelseJoinDTO>();
			getRejseafregningAnvisningJoinListStmt.setString(1, navn);
			rs = getRejseafregningAnvisningJoinListStmt.executeQuery();
			
			while (rs.next()) {
				rejseafregningListe.add(new GodkendelseJoinDTO(rs.getString("navn"), rs.getInt("rejseafregning_ID"),
						rs.getString("nameProjekt"), rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("land"), 
						rs.getString("city"), rs.getString("anledning")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getAnvisninerJoin fejlede");
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
	public List<GodkendelseJoinDTO> getRejseafregningGodkendelseList(String navn) throws DALException {
		List<GodkendelseJoinDTO> rejseafregningListe = null;
		ResultSet rs = null;
		try {
			rejseafregningListe = new ArrayList<GodkendelseJoinDTO>();
			getRejseafregningGodkendelseJoinListStmt.setString(1, navn);
			rs = getRejseafregningGodkendelseJoinListStmt.executeQuery();
			while (rs.next()) {
				rejseafregningListe.add(new GodkendelseJoinDTO(rs.getString("navn"), rs.getInt("rejseafregning_ID"),
						rs.getString("nameProjekt"), rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("land"), 
						rs.getString("city"), rs.getString("anledning")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getAnvisninerJoin fejlede");
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
	public void updateRejseafregningStatus(int rejseafregningID, String status) throws DALException {
		try {
			// Argumenter til statement
			updateStatusStmt.setString(1, status);
			updateStatusStmt.setInt(2, rejseafregningID);

			// Kald til database
			updateStatusStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateStatus fejlede");
		}
	}
}
