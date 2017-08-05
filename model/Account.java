package model;

public class Account {
	private String name;
	private String pass;

	public Account(String name, String pass) {
		this.name = name;
		this.pass = pass;
	}

	public void setName(String name) {
		this.name = name;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() { return name; }
	public String getPass() { return pass; }

}
