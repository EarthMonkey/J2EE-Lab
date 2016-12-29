package edu.nju.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.nju.dao.DaoHelper;

public class DaoHelperImpl implements DaoHelper {

	private static DaoHelperImpl baseDao = new DaoHelperImpl();

	private Connection connection = null;

	private DaoHelperImpl() {

		String driver = "com.mysql.jdbc.Driver";

		try {
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		System.out.println("About to get ds---DaoHelper");
	}

	public static DaoHelperImpl getBaseDaoInstance() {
		return baseDao;
	}

	@Override
	public Connection getConnection() {
		String url = "jdbc:mysql://localhost:3306/J2eeLab?characterEncoding=utf8&useSSL=true";
		String username = "root";
		String password = "1215";

		try {
			connection = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return connection;
	}

	@Override
	public void closeConnection(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closePreparedStatement(PreparedStatement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public void closeResult(ResultSet result) {
		if (result != null) {
			try {
				result.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
