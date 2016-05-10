package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IRejseafregningDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.GodkendelseJoinDTO;
import dtu.rejseafregning.shared.GodtgoerelseDTO;
import dtu.rejseafregning.shared.RejseafregningDTO;
import dtu.rejseafregning.shared.RejseafregningSum;
import dtu.rejseafregning.shared.RejsedagDTO;
import dtu.rejseafregning.shared.UdgiftDTO;

public class RejseafregningDAO extends RemoteServiceServlet implements IRejseafregningDAO {

	private PreparedStatement getRejseafregningStmt = null;
	private PreparedStatement getRejseafregningIDStmt = null;
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
	
	private RejseafregningSum sumBeregner;
	private UdgiftDAO udgiftDAO;
	private RejsedagDAO rejsedagDAO;
	private GodtgoerelseDAO godtgoerelseDAO;

	public RejseafregningDAO() throws Exception {

		new Connector();
		
		sumBeregner = new RejseafregningSum();
		udgiftDAO = new UdgiftDAO();
		rejsedagDAO = new RejsedagDAO();
		godtgoerelseDAO = new GodtgoerelseDAO();

		// getRejseafregning statement
		getRejseafregningStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE rejseafregning_ID = ?");

		// getRejseafregningList statement
		getRejseafregningListStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE brugernavn = ?");

		// getRejseafregningNavnList statement
		getRejseafregningListNavnStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_ID, navn, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "rejseafregning.anviser, rejseafregning.godkender, sum, refunderes "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND medarbejder.navn = ?");

		// getRejseafregningStatList statement
		getRejseafregningListStatStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_ID, navn, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "rejseafregning.anviser, rejseafregning.godkender, sum, refunderes "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND status = ?");

		// getRejseafregningNavnStatList statement
		getRejseafregningListNavnStatStmt = Connector.conn.prepareStatement(
				"SELECT rejseafregning_ID, navn, nameProjekt, status, datoStart, datoSlut, land, city, anledning, "
				+ "rejseafregning.anviser, rejseafregning.godkender, sum, refunderes "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND medarbejder.navn = ? AND status = ?");
		
		// getRejseafregningAnvisningJoinList
		getRejseafregningAnvisningJoinListStmt = Connector.conn.prepareStatement("SELECT medarbejder.navn, rejseafregning.* "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND anviser = ? AND status = 'Til anvisning'");
		
		// getRejseafregningGodkendelseJoinList
		getRejseafregningGodkendelseJoinListStmt = Connector.conn.prepareStatement("SELECT medarbejder.navn, rejseafregning.* "
				+ "FROM medarbejder, rejseafregning "
				+ "WHERE medarbejder.brugernavn = rejseafregning.brugernavn AND godkender = ? AND status = 'Til godkendelse'");
		
		// getRejseafregningsUdkastList statement
		getRejseafregningUdkastListStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE brugernavn = ? AND status = 'Udkast'");

		// getRejseafregningsCirkulationList statement
		getRejseafregningCirkulationListStmt = Connector.conn
				.prepareStatement("SELECT * FROM rejseafregning WHERE brugernavn = ? "
						+ "AND status != 'Udkast' AND status != 'Sendt til Oracle'");

		getRejseafregningAfsluttedeListStmt = Connector.conn.prepareStatement(
				"SELECT * FROM rejseafregning WHERE brugernavn = ? " + "AND status = 'Sendt til Oracle'");

		// createRejseafregning statement
		createRejseafregningStmt = Connector.conn
				.prepareStatement("INSERT INTO rejseafregning (brugernavn, nameProjekt, land, status, datoStart, datoSlut, city, anledning, "
						+ "anviser, godkender, forklaring, sum, refunderes) VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
		
		getRejseafregningIDStmt = Connector.conn.prepareStatement("SELECT rejseafregning_ID FROM rejseafregning WHERE brugernavn = ? AND "
				+ "nameProjekt = ? AND land = ? AND datoStart = ? AND datoSlut = ? AND city = ? AND anledning = ?");

		// updateRejseafregning statement
		updateRejseafregningStmt = Connector.conn.prepareStatement(
				"UPDATE rejseafregning SET brugernavn = ?, nameProjekt = ?, land = ?, status = ?, datoStart = ?, "
				+ "datoSlut = ?, city = ?, anledning = ?, anviser = ?, godkender = ?, forklaring = ?, sum = ?, refunderes = ? "
				+ "WHERE rejseafregning_ID = ?");

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
				return new RejseafregningDTO(rs.getInt("rejseafregning_ID"), rs.getString("brugernavn"),
						rs.getString("godkender"), rs.getString("anviser"), rs.getString("land"),
						rs.getString("city"), rs.getString("anledning"), rs.getString("forklaring"), rs.getString("status"), rs.getDate("datoStart"),
						rs.getDate("datoSlut"), rs.getString("nameProjekt"), rs.getDouble("sum"), rs.getDouble("refunderes"));
			throw new DALException("Rejseafregning findes ikke!");
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejseafregning fejlede: "+e.getMessage());
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
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), rs.getDouble("sum"), rs.getDouble("refunderes")));
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
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), rs.getDouble("sum"), rs.getDouble("refunderes")));
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
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), rs.getDouble("sum"), rs.getDouble("refunderes")));
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
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), rs.getDouble("sum"), rs.getDouble("refunderes")));
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
						rs.getString("navn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), "", rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), rs.getDouble("sum"), rs.getDouble("refunderes")));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet getRejseafregningListNavn fejlede");
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
						rs.getString("navn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), "", rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), 
						rs.getDouble("sum"), rs.getDouble("refunderes")));
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
			rs = getRejseafregningListNavnStatStmt.executeQuery();

			while (rs.next()) {
				RejseafregningListe.add(new RejseafregningDTO(rs.getInt("rejseafregning_ID"),
						rs.getString("navn"), rs.getString("godkender"), rs.getString("anviser"),
						rs.getString("land"), rs.getString("city"), rs.getString("anledning"), "", rs.getString("status"),
						rs.getDate("datoStart"), rs.getDate("datoSlut"), rs.getString("nameProjekt"), 
						rs.getDouble("sum"), rs.getDouble("refunderes")));
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
						rs.getString("city"), rs.getString("anledning"), rs.getDouble("sum"), rs.getDouble("refunderes")));
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
						rs.getString("city"), rs.getString("anledning"), rs.getDouble("sum"), rs.getDouble("refunderes")));
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

	@Override
	public void createRejseafregning(RejseafregningDTO rejseafregning) throws DALException {
		try {
			// Argumenter ins�ttes
			createRejseafregningStmt.setString(1, rejseafregning.getMedarbejderNavn());
			createRejseafregningStmt.setString(2, rejseafregning.getProjektNavn());
			createRejseafregningStmt.setString(3, rejseafregning.getLand());
			createRejseafregningStmt.setString(4, rejseafregning.getStatus());
			createRejseafregningStmt.setDate(5, new java.sql.Date(rejseafregning.getStartDato().getTime()));
			createRejseafregningStmt.setDate(6, new java.sql.Date(rejseafregning.getSlutDato().getTime()));
			createRejseafregningStmt.setString(7, rejseafregning.getBy());
			createRejseafregningStmt.setString(8, rejseafregning.getAnledning());
			createRejseafregningStmt.setString(9, rejseafregning.getAnviserNavn());
			createRejseafregningStmt.setString(10, rejseafregning.getGodkenderNavn());
			createRejseafregningStmt.setString(11, rejseafregning.getForklaring());
			createRejseafregningStmt.setDouble(12, rejseafregning.getSum());
			createRejseafregningStmt.setDouble(13, rejseafregning.getRefunderes());

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
			calcSumRefunderes(rejseafregning);
			updateRejseafregningStmt.setString(1, rejseafregning.getMedarbejderNavn());
			updateRejseafregningStmt.setString(2, rejseafregning.getProjektNavn());
			updateRejseafregningStmt.setString(3, rejseafregning.getLand());
			updateRejseafregningStmt.setString(4, rejseafregning.getStatus());
			updateRejseafregningStmt.setDate(5, new java.sql.Date(rejseafregning.getStartDato().getTime()));
			updateRejseafregningStmt.setDate(6, new java.sql.Date(rejseafregning.getSlutDato().getTime()));
			updateRejseafregningStmt.setString(7, rejseafregning.getBy());
			updateRejseafregningStmt.setString(8, rejseafregning.getAnledning());
			updateRejseafregningStmt.setString(9, rejseafregning.getAnviserNavn());
			updateRejseafregningStmt.setString(10, rejseafregning.getGodkenderNavn());
			updateRejseafregningStmt.setString(11, rejseafregning.getForklaring());
			// Set sum!
			updateRejseafregningStmt.setDouble(12, rejseafregning.getSum());
			updateRejseafregningStmt.setDouble(13, rejseafregning.getRefunderes());
			updateRejseafregningStmt.setInt(14, rejseafregning.getRejseafregningID());
			// Kald til database
			updateRejseafregningStmt.executeUpdate();
		} catch (SQLException e) {
			throw new DALException("Kaldet updateRejseafregning fejlede: "+e.getMessage());
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
	public int getRejseafregningID(String brugernavn, String nameProjekt, String land, Date datoStart, Date datoSlut, String city, 
			String anledning) throws DALException {
		// SELECT * FROM rejseafregning WHERE brugernavn = ?, nameProjekt = ?, land = ?, datoStart = ?, datoSlut = ?, city = ?, anledning = ?
		try {
			getRejseafregningIDStmt.setString(1, brugernavn);
			getRejseafregningIDStmt.setString(2, nameProjekt);
			getRejseafregningIDStmt.setString(3, land);
			getRejseafregningIDStmt.setDate(4, new java.sql.Date(datoStart.getTime()));
			getRejseafregningIDStmt.setDate(5, new java.sql.Date(datoSlut.getTime()));
			getRejseafregningIDStmt.setString(6, city);
			getRejseafregningIDStmt.setString(7, anledning);
			ResultSet rs = getRejseafregningIDStmt.executeQuery();
			if (rs.last()) return rs.getInt("rejseafregning_ID");
			throw new DALException("getRejseafregningID fejlede: Rejseafregning findes ikke!");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}
	
	private void calcSumRefunderes(RejseafregningDTO r) throws DALException {
		try {
			List<RejsedagDTO> rejsedage = rejsedagDAO.getRejsedagList(r.getRejseafregningID());
			List<UdgiftDTO> udgiftDTO = udgiftDAO.getUdgiftList(r.getRejseafregningID());
			GodtgoerelseDTO godtDTO = godtgoerelseDAO.getGodtgoerelse(r.getLand());
			sumBeregner.beregnSum(r, rejsedage, udgiftDTO, godtDTO);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
