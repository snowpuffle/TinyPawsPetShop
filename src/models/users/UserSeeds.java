package models.users;

import java.util.*;

public class UserSeeds {
	private ArrayList<User> listOfUsers;
	private static Random random = new Random();

	// Default Constructor Class
	public UserSeeds() {
		// Create New List Instances
		this.listOfUsers = new ArrayList<User>();
	}

	// Generate Users
	public void generateUsers() {
		// Generate Attributes
		int ID = generateID();
		String username = "admin";
		String password = "test";

		// Create & Initialize User Object
		User user = new User(ID, username, password);
		listOfUsers.add(user);
		// System.out.println(user.toString());
	}

	// Generate Random ID
	public int generateID() {
		int minID = 10000;
		int maxID = 99999;

		return random.nextInt(maxID - minID + 1) + minID;
	}

	// Get User List
	public ArrayList<User> getUserList() {
		return listOfUsers;
	}
}