package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Login;
import swing.ErrorDialog;

public class FollowingListDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/test/java";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public ArrayList<String> LoadFollowingList() {
		ArrayList<String> al = new ArrayList<String>();
		String userName = Login.getStaticName();
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "SELECT FOLLOWED FROM FOLLOW WHERE FOLLOWING = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			al = new ArrayList<String>();
			while ( rs.next() ) {
				String follower = rs.getString("FOLLOWED");
				al.add(follower);
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
					ErrorDialog.stackTraceDialog(e);
					return null;
				}
			}
		}
		return al;
	}

	public boolean SaveFollowingListDAO(ArrayList<String> al) {
		Connection conn = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String userName = Login.getStaticName();
			String sql_delete = "DELETE FROM FOLLOW WHERE FOLLOWING = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql_delete);
			pStmt.executeUpdate();

			String sql_insert = "INSERT INTO FOLLOW(FOLLOWING, FOLLOWED) VALUES(?, ?)";
			pStmt = conn.prepareStatement(sql_insert);

			for ( int i = 0; i < al.size(); i++ ) {
				pStmt.setString(1, userName);
				pStmt.setString(2, al.get(i));
				pStmt.executeUpdate();
			}

			String sql = "SELECT FOLLOWING, FOLLOWED FROM FOLLOW WHERE FOLLOWING = '" + userName + "'";
			pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			if ( Login.getStaticName().equals("java") ) {
				System.out.println("Debug::Start");
				while ( rs.next() ) {
					String following = rs.getString("FOLLOWING");
					String followed = rs.getString("FOLLOWED");
					System.out.println(following + ": " + followed);
				}
				System.out.println("Debug::End");
			}
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
}
