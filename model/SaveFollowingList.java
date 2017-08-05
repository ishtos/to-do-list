package model;

import java.util.ArrayList;

import dao.FollowingListDAO;

public class SaveFollowingList {
	public SaveFollowingList(ArrayList<String> al) {
		FollowingListDAO flDAO = new FollowingListDAO();
		flDAO.SaveFollowingListDAO(al);
	}
}
