package model;

import java.util.ArrayList;

import dao.FollowingListDAO;

public class CreateFollowingList {
	ArrayList<String> fal;

	public CreateFollowingList() {
		FollowingListDAO flDAO = new FollowingListDAO();
		fal = flDAO.LoadFollowingList();
	}

	public ArrayList<String> getFollowingList() {
		return fal;
	}

}
