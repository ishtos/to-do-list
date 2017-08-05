package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import swing.ErrorDialog;

public class UserListDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/test/java";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public ArrayList<String> LoadUserList() {
		ArrayList<String> al;
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "SELECT NAME FROM ACCOUNT";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			al = new ArrayList<String>();
			while ( rs.next() ) {
				String task = rs.getString("NAME");
				al.add(task);
			}

		} catch (SQLException e) {
			ErrorDialog.stackTraceDialog(e);
			return null;

		} catch (ClassNotFoundException e) {
			ErrorDialog.stackTraceDialog(e);
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

	public boolean removeUserList(String name) {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql_account = "DELETE ACCOUNT WHERE NAME = '" + name + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql_account);

			pStmt.executeUpdate();

			String sql_followed = "DELETE FOLLOW WHERE FOLLOWED = '" + name + "'";
			pStmt = conn.prepareStatement(sql_followed);

			pStmt.executeUpdate();

			String sql_following = "DELETE FOLLOW WHERE FOLLOWING = '" + name + "'";
			pStmt = conn.prepareStatement(sql_following);

			pStmt.executeUpdate();

			String sql_list = "DELETE LIST WHERE NAME = '" + name + "'";
			pStmt = conn.prepareStatement(sql_list);

			pStmt.executeUpdate();

			String sql_sender = "DELETE APPROVAL WHERE SENDER = '" + name + "'";
			pStmt = conn.prepareStatement(sql_sender);

			pStmt.executeUpdate();

			String sql_receiver = "DELETE APPROVAL WHERE RECEIVER = '" + name + "'";
			pStmt = conn.prepareStatement(sql_receiver);

			pStmt.executeUpdate();

			String sql_archive = "DELETE ARCHIVE WHERE NAME = '" + name + "'";
			pStmt = conn.prepareStatement(sql_archive);

			pStmt.executeUpdate();

		} catch (SQLException e) {
			ErrorDialog.stackTraceDialog(e);
			return false;

		} catch (ClassNotFoundException e) {
			ErrorDialog.stackTraceDialog(e);
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
}
