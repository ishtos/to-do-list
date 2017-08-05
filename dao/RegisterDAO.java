package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Account;
import model.Register;
import swing.ErrorDialog;

public class RegisterDAO {
	private final String DRIVER_NAME = "org.h2.Driver";
	private final String JDBC_URL = "jdbc:h2:~/test/java";
	private final String DB_USER = "sa";
	private final String DB_PASS = "";

	public Account insertIntoDB(Register register) {
		Connection conn = null;
		Account account = null;

		int nameLength = register.getName().length();
		int passLength = register.getPass().length();
		if ( nameLength == 0 || nameLength > 10 || passLength == 0 || passLength > 15 ) {
			return null;
		}

		try {
			Class.forName(DRIVER_NAME);
			conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASS);

			if ( register.getName().length() != 0 && register.getPass().length() != 0 ) {
				String sql_register = "INSERT INTO ACCOUNT (NAME, PASS) VALUES('" +
						register.getName() + "', '" + register.getPass() + "')";

				Statement st = conn.createStatement();
				st.executeUpdate(sql_register);

				String sql_confirm= "SELECT NAME, PASS FROM ACCOUNT WHERE NAME = ? AND PASS = ?";
				PreparedStatement pStmt = conn.prepareStatement(sql_confirm);
				pStmt.setString(1, register.getName());
				pStmt.setString(2, register.getPass());

				ResultSet rs = pStmt.executeQuery();

				if (rs.next()) {
					String pass = rs.getString("PASS");
					String name = rs.getString("NAME");

					account = new Account(pass, name);
				}
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
		return account;
	}
}
