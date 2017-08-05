package model;

public class Register {
	private String name;
	private String pass;

	public Register(String name, String pass) {
		this.name = name;
		this.pass = pass;	
	}

	public String getName() { return name; }
	public String getPass() { return pass; }
}
