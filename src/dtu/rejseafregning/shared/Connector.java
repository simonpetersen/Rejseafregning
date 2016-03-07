package dtu.rejseafregning.shared;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Connector {
	public static Connection connectToDatabase(String url, String username, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		// call the driver class' no argument constructor
		Class.forName("com.mysql.jdbc.Driver").newInstance();

		// get Connection-object via DriverManager
		return (Connection) DriverManager.getConnection(url, username, password);
	}

	private static Connection conn;
	private static Statement stmt;

	public Connector(String server, int port, String database, String username, String password)
			throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		conn = connectToDatabase("jdbc:mysql://" + server + ":" + port + "/" + database, username, password);
		stmt = conn.createStatement();
	}

	public Connector() throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException {
		this(Constant.server, Constant.port, Constant.database, Constant.username, Constant.password);
	}
	
	public static ResultSet doQuery(String cmd) throws DALException {
		try {
			return stmt.executeQuery(cmd);
		} catch (SQLException e) {
			throw new DALException(e);
		}
	}
	
		public static int doUpdate(String cmd) throws DALException {
			try {
				return stmt.executeUpdate(cmd);
			} catch (SQLException e) {
				throw new DALException(e);
			}
		}
}
