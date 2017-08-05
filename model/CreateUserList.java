package model;

import java.util.ArrayList;

import dao.UserListDAO;

public class CreateUserList {
	ArrayList<String> ual;

	public CreateUserList() {
		UserListDAO tdlDAO = new UserListDAO();
		ual = tdlDAO.LoadUserList();
	}

	public ArrayList<String> getUserList() {
		return ual;
	}

}