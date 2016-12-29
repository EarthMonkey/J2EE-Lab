package edu.nju.database;

import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.Connection;

public class DBConnection {
	
	public static Connection getConnection() {
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/J2eeLab?characterEncoding=utf8&useSSL=true";
		String username = "root";
		String password = "1215";
		Connection con = null;
		
		try {
			Class.forName(driver); 
			con = DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
}
