package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class cnDatabase {
	public static Connection getConnection() {
		Connection c = null;
		try {
			DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
			String url = "jdbc:mySQL://localhost:3306/quanlythuvien";
			String user = "root";
			String password = "123456";
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
