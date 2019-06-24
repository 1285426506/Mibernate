package com.gjj.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
	
	private static Connection connection;
	
	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			connection = (Connection) DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/chat_room", 
					"root", "549365gjj");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static Connection getConnection() {
		return connection;
	}
	
	public static void close() {
		try {
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}finally {
			connection = null;
		}
	}

}
