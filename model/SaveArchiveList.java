package model;

import java.util.ArrayList;

import dao.ToDoListDAO;

public class SaveArchiveList {
	public SaveArchiveList(ArrayList<String> al) {
		ToDoListDAO tdlDAO = new ToDoListDAO();
		tdlDAO.SaveArchiveListDAO(al);
	}
}
