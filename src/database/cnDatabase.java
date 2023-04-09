package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cnDatabase {
	public static Connection getConnection() {
		Connection c = null;
		try {
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			String url = "jdbc:mySQL://localhost:3306/...";
			String user = "root";
			String password = "";
			c = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return c;
	}
	
	public static void disConnection(Connection c) {
		if(c !=null) {
			try {
				c.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
