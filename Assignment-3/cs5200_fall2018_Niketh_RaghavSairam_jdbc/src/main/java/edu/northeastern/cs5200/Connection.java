package edu.northeastern.cs5200;

import java.sql.*;

public class Connection {
	private static Connection instance = null;

	private Connection() {
	}

	public static Connection getInstance() {
		if (instance == null)
			instance = new Connection();
		return instance;
	}

	private static final String DRIVER = "com.mysql.jdbc.Driver";
	private static final String URL = "jdbc:mysql://cs5200-fall2018-niketh-1.c6n4ywnhptua.us-east-2.rds.amazonaws.com:3306/cs5200_fall2018_Niketh_RaghavSairam_jdbc";
	private static final String USER = "raghav";
	private static final String PASSWORD = "password123";

	public static java.sql.Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName(DRIVER);
		return DriverManager.getConnection(URL, USER, PASSWORD);
	}

	public static void closeConnection(java.sql.Connection conn) {
		try {
			if (conn != null)
				conn.close();
		} catch (SQLException e) {

			e.printStackTrace();
		}
	}
}