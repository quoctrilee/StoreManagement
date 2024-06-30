package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
	public static Connection getconConnection() {
		Connection conn = null;
		String url = "jdbc:sqlserver://LAPTOP-74B2C9BT\\MSSQLSERVER01:1433;databaseName=StoreManagements;encrypt=false;";
		String username = "sa";
		String password = "17012005";
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
		
	}
	public static void closeConnection(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
