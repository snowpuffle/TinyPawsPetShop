package models.animals;

public class Animal {
	// Animal Attributes
	private int ID;
	private String type;
	private double price;
	private String name;
	private String dateOfBirth;
	private String breed;
	private String gender;
	private String status;
	private String imageURL;

	// Default Class Constructor
	public Animal(int ID, String type, double price, String name, String dateOfBirth, String breed, String gender,
			String status, String imageURL) {
		this.ID = ID;
		this.type = type;
		this.price = price;
		this.name = name;
		this.dateOfBirth = dateOfBirth;
		this.breed = breed;
		this.gender = gender;
		this.status = status;
		this.imageURL = imageURL;
	}

	// Getter & Setter Methods
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "ID: " + this.ID + "\tType: " + this.type + "\tPrice: " + this.price + "\tName: " + this.name + "\tDOB: "
				+ this.dateOfBirth + "\tBreed: " + this.breed + "\tGender: " + this.gender + "\tStatus: " + this.status
				+ "\tImageURL: " + this.imageURL;
	}
}