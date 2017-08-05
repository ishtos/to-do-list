package model;

import java.util.ArrayList;

import dao.ApprovalDAO;

public class Approval {
	public Approval() {

	}

	public static boolean approval(String name) {
		return ApprovalDAO.approval(name);
	}


	public static boolean sendRequest(String name) {
		return ApprovalDAO.sendRequest(name);
	}

	public ArrayList<String> loadRequest() {
		return ApprovalDAO.loadRequest();
	}

	public ArrayList<String> loadInit() {
		return ApprovalDAO.loadInit();
	}
}
