package model;

import java.util.ArrayList;

import dao.ToDoListDAO;

public class CreateToDoList {
	ArrayList<String> al;

	public CreateToDoList() {
		ToDoListDAO tdlDAO = new ToDoListDAO();
		al = tdlDAO.LoadToDoList();
	}

	public CreateToDoList(String name) {
		ToDoListDAO tdlDAO = new ToDoListDAO();
		al = tdlDAO.LoadToDoList(name);
	}

	public ArrayList<String> getArrayList() {
		return al;
	}

}
