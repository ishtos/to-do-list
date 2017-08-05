package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Login;

public class ApprovalDAO {
	private final static String DRIVER_NAME = "org.h2.Driver";
	private final static String JDBC_URL = "jdbc:h2:~/test/java";
	private final static String DB_USER = "sa";
	private final static String DB_PASS = "";


	public static boolean approval(String name) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String userName = Login.getStaticName();

			/*
			String sql_select = "SELECT SENDER, RECEIVER FROM APPROVAL WHERE SENDER = ? AND RECEIVER = ?";
			PreparedStatement  pStmt = conn.prepareStatement(sql_select);
			pStmt.setString(1, name);
			pStmt.setString(2, userName);

			pStmt.executeQuery();
			*/

			String sql_delete = "DELETE FROM APPROVAL WHERE SENDER = ? AND RECEIVER = ?";
			PreparedStatement pStmt = conn.prepareStatement(sql_delete);
			pStmt.setString(1, name);
			pStmt.setString(2, userName);
			pStmt.executeUpdate();

			String sql_insert = "INSERT INTO FOLLOW(FOLLOWING, FOLLOWED) VALUES(?, ?)";
			pStmt = conn.prepareStatement(sql_insert);
			pStmt.setString(1, name);
			pStmt.setString(2, userName);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}


	public static boolean sendRequest(String name) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String userName = Login.getStaticName();
			String sql_insert = "INSERT INTO APPROVAL(SENDER, RECEIVER) VALUES(?, ?)";
			PreparedStatement pStmt = conn.prepareStatement(sql_insert);
			pStmt.setString(1, userName);
			pStmt.setString(2, name);
			pStmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return false;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return false;
				}
			}
		}
		return true;
	}


	public static ArrayList<String> loadRequest() {
		Connection conn = null;
		ArrayList<String> al;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String userName = Login.getStaticName();
			String sql_select = "SELECT SENDER FROM APPROVAL WHERE RECEIVER = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql_select);

			ResultSet rs = pStmt.executeQuery();
			al = new ArrayList<String>();
			while ( rs.next() ) {
				String receiver = rs.getString("SENDER");
				al.add(receiver);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return al;
	}

	public static ArrayList<String> loadInit() {
		Connection conn = null;
		ArrayList<String> al;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String userName = Login.getStaticName();
			String sql_select = "SELECT RECEIVER FROM APPROVAL WHERE SENDER = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql_select);

			ResultSet rs = pStmt.executeQuery();
			al = new ArrayList<String>();
			while ( rs.next() ) {
				String receiver = rs.getString("RECEIVER");
				al.add(receiver);
			}


		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
					return null;
				}
			}
		}
		return al;
	}
}
