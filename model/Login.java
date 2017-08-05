package model;

public class Login {
	private static String Name;

	private String name;
	private String pass;

	public Login(String name, String pass) {
		this.name = name;
		this.pass = pass;
		Login.Name = name;
	}

	public static String getStaticName() { return Name; }
	public String getName() { return name; }
	public String getPass() { return pass; }
}
