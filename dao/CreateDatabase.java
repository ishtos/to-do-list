package dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import swing.ErrorDialog;

public class CreateDatabase {
	private final static String DRIVER_NAME = "org.h2.Driver";
	private final static String JDBC_URL = "jdbc:h2:~/test/java";
	private final static String DB_USER = "sa";
	private final static String DB_PASS = "";

	public static void main(String[] args) {
		Connection conn = null;
		Statement stmt = null;

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			stmt = conn.createStatement();

			String drop_account_table = "DROP TABLE IF EXISTS ACCOUNT;";
			stmt.executeUpdate(drop_account_table);

	        String drop_archive_table = "DROP TABLE IF EXISTS ARCHIVE;";
			stmt.executeUpdate(drop_archive_table);

			String drop_follow_table = "DROP TABLE IF EXISTS FOLLOW;";
			stmt.executeUpdate(drop_follow_table);

			String drop_list_table = "DROP TABLE IF EXISTS LIST;";
			stmt.executeUpdate(drop_list_table);

			String drop_approval_table = "DROP TABLE IF EXISTS APPROVAL;";
			stmt.executeUpdate(drop_approval_table);

			String create_account_table =
					"CREATE TABLE ACCOUNT (" +
					"NAME VARCHAR(10) PRIMARY KEY NOT NULL," +
					"PASS VARCHAR(15) NOT NULL" +
					");";
			stmt.executeUpdate(create_account_table);

			String create_archive_table =
					"CREATE TABLE ARCHIVE (" +
					"ID INT AUTO_INCREMENT," +
					"NAME VARCHAR(10) NOT NULL, " +
					"TASK VARCHAR(30) NOT NULL" +
					");";
			stmt.executeUpdate(create_archive_table);

			String create_follow_table =
					"CREATE TABLE FOLLOW (" +
					"FOLLOWING VARCHAR(10) NOT NULL," +
					"FOLLOWED VARCHAR(10) NOT NULL" +
					");";
			stmt.executeUpdate(create_follow_table);

			String create_list_table =
					"CREATE TABLE LIST (" +
					"ID INT AUTO_INCREMENT," +
					"NAME VARCHAR(10) NOT NULL, " +
					"TASK VARCHAR(30) NOT NULL" +
					");";
			stmt.executeUpdate(create_list_table);

			String create_approval_table =
					"CREATE TABLE APPROVAL (" +
					"SENDER VARCHAR(10) NOT NULL," +
					"RECEIVER VARCHAR(10) NOT NULL" +
					");";
			stmt.executeUpdate(create_approval_table);

			System.out.println("success");
		} catch (SQLException e) {
			ErrorDialog.stackTraceDialog(e);
		} catch (ClassNotFoundException e) {
			ErrorDialog.stackTraceDialog(e);
		} finally {
			if (stmt != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
			if (conn != null) {
				try {
					conn.close();
				} catch(SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
