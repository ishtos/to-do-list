package model;

import dao.UserListDAO;

public class RemoveUserList {
	public RemoveUserList() {

	}

	public boolean removeUserList(String name) {
		UserListDAO tdlDAO = new UserListDAO();
		return tdlDAO.removeUserList(name);
	}
}
