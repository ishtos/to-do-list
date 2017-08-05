package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import model.Login;
import swing.ErrorDialog;

public class ToDoListDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/test/java";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public boolean SaveToDoListDAO(ArrayList<String> al) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String userName = Login.getStaticName();
			String sql_delete = "DELETE FROM LIST WHERE NAME = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql_delete);
			pStmt.executeUpdate();

			String sql_insert = "INSERT INTO LIST(NAME, TASK) VALUES(?, ?)";
			pStmt = conn.prepareStatement(sql_insert);

			for ( int i = 0; i < al.size(); i++ ) {
				pStmt.setString(1, userName);
				pStmt.setString(2, al.get(i));
				pStmt.executeUpdate();
			}

			String sql = "SELECT NAME, TASK FROM LIST ORDER BY ID DESC";
			pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			if ( Login.getStaticName().equals("java") ) {
				System.out.println("Debug::Start");
				while ( rs.next() ) {
					String name = rs.getString("NAME");
					String task = rs.getString("TASK");
					System.out.println(name + ": " + task);
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

	public boolean SaveArchiveListDAO(ArrayList<String> al) {
		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			String userName = Login.getStaticName();
			String sql_delete = "DELETE FROM ARCHIVE WHERE NAME = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql_delete);
			pStmt.executeUpdate();

			String sql_insert = "INSERT INTO ARCHIVE(NAME, TASK) VALUES(?, ?)";
			pStmt = conn.prepareStatement(sql_insert);

			for ( int i = 0; i < al.size(); i++ ) {
				pStmt.setString(1, userName);
				pStmt.setString(2, al.get(i));
				pStmt.executeUpdate();
			}

			String sql = "SELECT NAME, TASK FROM ARCHIVE";
			pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			if ( Login.getStaticName().equals("java") ) {
				System.out.println("Debug::Start");
				while ( rs.next() ) {
					String name = rs.getString("NAME");
					String task = rs.getString("TASK");
					System.out.println(name + ": " + task);
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

	public ArrayList<String> LoadArchiveList() {
		ArrayList<String> al;
		String userName = Login.getStaticName();

		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "SELECT TASK FROM ARCHIVE WHERE NAME = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			al = new ArrayList<String>();
			while ( rs.next() ) {
				String task = rs.getString("TASK");
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


	public ArrayList<String> LoadToDoList() {
		ArrayList<String> al;
		String userName = Login.getStaticName();

		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "SELECT TASK FROM LIST WHERE NAME = '" + userName + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			al = new ArrayList<String>();
			while ( rs.next() ) {
				String task = rs.getString("TASK");
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

	public ArrayList<String> LoadToDoList(String name) {
		ArrayList<String> al;

		Connection conn = null;
		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);
			String sql = "SELECT TASK FROM LIST WHERE NAME = '" + name + "'";
			PreparedStatement pStmt = conn.prepareStatement(sql);

			ResultSet rs = pStmt.executeQuery();

			al = new ArrayList<String>();
			while ( rs.next() ) {
				String task = rs.getString("TASK");
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
}
