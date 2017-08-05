package model;

import dao.RegisterDAO;

public class RegisterLogic {
	public boolean excute(Register register) {
		RegisterDAO dao = new RegisterDAO();
		Account account = dao.insertIntoDB(register);
		return account != null;
	}
}
