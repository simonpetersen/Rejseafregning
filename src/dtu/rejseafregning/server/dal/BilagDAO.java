package dtu.rejseafregning.server.dal;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.omg.CORBA.portable.InputStream;

import dtu.rejseafregning.client.services.IBilagDAO;
import dtu.rejseafregning.shared.BilagDTO;
import dtu.rejseafregning.shared.DALException;

public class BilagDAO implements IBilagDAO {

	private PreparedStatement getBilagStmt = null;
	private PreparedStatement getBilagListStmt = null;
	private PreparedStatement createBilagStmt = null;
	private PreparedStatement updateBilagStmt = null;
	private PreparedStatement deleteBilagStmt = null;

	public BilagDAO() throws Exception {
		try {
			new Connector();

			// GetBilag Statement
			getBilagStmt = Connector.conn.prepareStatement("SELECT * FROM Bilag WHERE Bilag_ID = ?");

			// GetBilagList Statement
			getBilagListStmt = Connector.conn.prepareStatement("SELECT * FROM Bilag");

			// createBilag Statement
			createBilagStmt = Connector.conn.prepareStatement("INSERT INTO Bilag VALUES (?, ?)");

			// updateBilag Statement
			updateBilagStmt = Connector.conn.prepareStatement("UPDATE Bilag SET billede = ? WHERE Bilag_ID = ?");

			// deleteBilag Statement
			deleteBilagStmt = Connector.conn.prepareStatement("DELETE FROM Bilag WHERE Bilag_ID = ?");
		} catch (SQLException e) {
			throw new DALException("Problemer med forbindelsen til databasen.");
		}
	}

	@Override
	public BilagDTO getBilag(int bilagID) throws DALException, IOException {
		ResultSet rs = null;
		try {
			getBilagStmt.setInt(1, bilagID);

			rs = getBilagStmt.executeQuery();
			
			//File oprettes og outputstream indlæser billedet til filen.
			File image = new File("D:\\java.png");
			FileOutputStream fos = new FileOutputStream(image);

			byte[] buffer = new byte[1];
			InputStream is = (InputStream) rs.getBinaryStream("Billed");
			while (is.read(buffer) > 0) {
				fos.write(buffer);
			}
			fos.close();
			return new BilagDTO(rs.getInt("Bilag_ID"), image);
		} catch (SQLException e) {
			throw new DALException("Kaldet: getBilag fejlede");
		}
	}

	@Override
	public List<BilagDTO> getBilagList() throws DALException, IOException {
		List<BilagDTO> BilagListe = null;
		ResultSet rs = null;
		try {
			rs = getBilagListStmt.executeQuery();
			BilagListe = new ArrayList<BilagDTO>();
			while (rs.next()) {
				File image = new File("D:\\java.png");
				FileOutputStream fos = new FileOutputStream(image);

				byte[] buffer = new byte[1];
				InputStream is = (InputStream) rs.getBinaryStream("Billed");
				while (is.read(buffer) > 0) {
					fos.write(buffer);
				}
				fos.close();
				BilagListe.add(new BilagDTO(rs.getInt("Bilag_ID"), image));
			}
		} catch (SQLException e) {
			throw new DALException("Kaldet: getBilagListe fejlede.");
		} finally {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
				close();
			}
		}
		return BilagListe;
	}

	@Override
	public void createBilag(BilagDTO bilag) throws DALException, IOException {
		try {
			createBilagStmt.setInt(1, bilag.getBilagID());

			// Billede oprettes og sættes som argument i statement
			File image = bilag.getBillede();
			FileInputStream fis = new FileInputStream(image);
			createBilagStmt.setBinaryStream(2, fis, (int) image.length());
			
			//kald til databasen
			createBilagStmt.executeUpdate();
			
			//Inputstream lukkes
			fis.close();
		} catch (SQLException e) {
			throw new DALException("Kaldet: createBilag fejlede.");
		}
	}

	@Override
	public void updateBilag(BilagDTO bilag) throws DALException, IOException {
		try {
			//Billede til inputstream
			File image = bilag.getBillede();
			FileInputStream fis = new FileInputStream(image);
			
			//argumenter sættes i statement
			updateBilagStmt.setBinaryStream(1, fis, (int) image.length());
			updateBilagStmt.setInt(1, bilag.getBilagID());
			
			//kald til databasen
			updateBilagStmt.executeUpdate();
			
			//Inputstream lukkes
			fis.close();
		} catch (SQLException e) {
			throw new DALException("Kaldet UpdateBilag fejlede.");
		}

	}

	@Override
	public void deleteBilag(BilagDTO bilag) throws DALException {
		try {
			//BilagID indsættes i statement
			deleteBilagStmt.setInt(1, bilag.getBilagID());
		} catch (SQLException e) {
			throw new DALException("Kaldet deleteBilag fejlede.");
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
