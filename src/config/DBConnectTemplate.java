package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectTemplate {

	private static final String url = "jdbc:mysql://localhost:3306/test";
	private static final String user = "user";
	private static final String password = "password";
	
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection (url,user,password);
		} catch (SQLException e) {
			System.out.println("データベース接続エラー: " + e.getMessage());
			throw e;
		}
	}
}
