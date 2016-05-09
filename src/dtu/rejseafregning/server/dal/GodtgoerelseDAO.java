package dtu.rejseafregning.server.dal;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;

import dtu.rejseafregning.client.services.IGodtgoerelseDAO;
import dtu.rejseafregning.shared.DALException;
import dtu.rejseafregning.shared.GodtgoerelseDTO;

public class GodtgoerelseDAO extends RemoteServiceServlet implements IGodtgoerelseDAO {

	private static final long serialVersionUID = 1L;
	private PreparedStatement getGodtgoerelseStmt;
	private PreparedStatement getGodtgoerelseListStmt;
	
	public GodtgoerelseDAO() throws Exception {
		getGodtgoerelseStmt = Connector.conn.prepareStatement("SELECT * FROM godtgoerelse WHERE land = ?");
		getGodtgoerelseListStmt = Connector.conn.prepareStatement("SELECT * FROM godtgoerelse");
	}
	
	@Override
	public GodtgoerelseDTO getGodtgoerelse(String land) throws Exception {
		ResultSet rs = null;
		try {
			getGodtgoerelseStmt.setString(1, land);
			rs = getGodtgoerelseStmt.executeQuery();
			if (rs.first()) {
				return new GodtgoerelseDTO(rs.getString("land"), rs.getInt("hoteldisposition"), rs.getDouble("dagpengesats"), 
						rs.getDouble("timepengesats"));
			}
			throw new DALException("godtgoerelse findes ikke!");
		} catch (SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

	@Override
	public List<GodtgoerelseDTO> getGodtgoerelseList() throws Exception {
		ResultSet rs = null;
		List<GodtgoerelseDTO> list;
		try {
			list = new ArrayList<GodtgoerelseDTO>();
			rs = getGodtgoerelseListStmt.executeQuery();
			while (rs.next()) list.add(new GodtgoerelseDTO(rs.getString("land"), rs.getInt("hoteldisposition"), rs.getDouble("dagpengesats"), 
						rs.getDouble("timepengesats")));
			return list;
		} catch(SQLException e) {
			throw new DALException(e.getMessage());
		}
	}

}
