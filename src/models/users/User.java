package models.users;

public class User {
	// User Attributes
	private int ID;
	private String userName;
	private String password;

	// Default Class Constructor
	public User(int ID, String userName, String password) {
		this.ID = ID;
		this.userName = userName;
		this.password = password;
	}

	// Getter & Setter Methods
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "ID: " + this.ID + "\tUserName: " + this.userName + "\tPassword: " + this.password;
	}

}