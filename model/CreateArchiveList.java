package model;

import java.util.ArrayList;

import dao.ToDoListDAO;

public class CreateArchiveList {
	ArrayList<String> al;

	public CreateArchiveList() {
		ToDoListDAO tdlDAO = new ToDoListDAO();
		al = tdlDAO.LoadArchiveList();
	}

	public ArrayList<String> getArrayList() {
		return al;
	}

}
