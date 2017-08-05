package model;

import dao.AccountDAO;

public class LoginLogic {
	public boolean excute(Login login) {
		AccountDAO dao = new AccountDAO();
		Account account = dao.findByLogin(login);
		return account != null;
	}
}
