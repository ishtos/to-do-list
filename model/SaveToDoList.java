package model;

import java.util.ArrayList;

import dao.ToDoListDAO;

public class SaveToDoList {
	public SaveToDoList(ArrayList<String> al) {
		ToDoListDAO tdlDAO = new ToDoListDAO();
		tdlDAO.SaveToDoListDAO(al);
	}
}
