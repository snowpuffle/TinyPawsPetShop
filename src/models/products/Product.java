package models.products;

public class Product {
	// Product Attribute
	private int ID;
	private String type;
	private double price;
	private String name;
	private int quantity;
	private String status;
	private String size;
	private String imageURL;

	// Default Class Constructor
	public Product(int ID, String type, double price, String name, int quantity, String status, String size,
			String imageURL) {
		this.ID = ID;
		this.type = type;
		this.price = price;
		this.name = name;
		this.quantity = quantity;
		this.status = status;
		this.size = size;
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

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	@Override
	public String toString() {
		return "ID: " + this.ID + "\tType: " + this.type + "\tPrice: " + this.price + "\tName: " + this.name
				+ "\tQuantity: " + this.quantity + "\tStatus: " + this.status + "\tSize: " + this.size + "\tImageURL: "
				+ this.imageURL;
	}

}