package models.products;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class ProductSeeds {
	private ArrayList<Product> listOfProducts;
	private static Random random = new Random();

	// Product Attributes
	private static String[] TYPES = { "FOOD", "TOY", "ACCESSORY" };
	private static String[] STATUS = { "AVAILABLE", "NOT AVAILABLE" };
	private static String[] SIZES = { "SMALL", "MEDIUM", "LARGE" };

	// Food Attributes
	private static String[] FOOD_NAMES = { "Blue Buffalo Life", "Purina Pro Diet", "Iams ProActive Health",
			"Merrick Classic", "Wellness Complete Health", "Nature's Recipe", "Taste of the Wild", "Instinct Original",
			"Diamond Naturals Meal", "Victor Classic Meal", "Pedigree Nutrition" };
	private static String[] FOOD_IMAGES = { "food-1.png", "food-2.png", "food-3.png", "food-4.png", "food-5.png",
			"food-6.png" };

	// Toy Attributes
	private static String[] TOY_NAMES = { "Chew Toy", "Squeaky Toy", "Ball Toy", "Tug Toy", "Cat Toy", "Rope Toy" };
	@SuppressWarnings("serial")
	private static final Map<String, String> TOY_IMAGES = new HashMap<String, String>() {
		{
			put("Chew Toy", "chew-toy.png");
			put("Squeaky Toy", "squeaky-toy.png");
			put("Ball Toy", "ball.png");
			put("Tug Toy", "tug-toy.png");
			put("Cat Toy", "cat-toy.png");
			put("Rope Toy", "rope-toy.png");
		}
	};

	// Accessory Attributes
	private static String[] ACCESSORY_NAMES = { "Collar", "Leash", "Feeder", "Bed", "Carrier", "Brush", "Shampoo" };
	@SuppressWarnings("serial")
	private static final Map<String, String> ACCESSORY_IMAGES = new HashMap<String, String>() {
		{
			put("Collar", "collar.png");
			put("Leash", "leash.png");
			put("Feeder", "feeder.png");
			put("Bed", "bed.png");
			put("Carrier", "carrier.png");
			put("Brush", "brush.png");
			put("Shampoo", "shampoo.png");
		}
	};

	// Default Class Constructor
	public ProductSeeds() {
		// Create New List Instances
		this.listOfProducts = new ArrayList<Product>();
	}

	// Generate Products
	public void generateProducts(int numberOfProducts) {
		// Generate X Number of Products
		for (int i = 0; i < numberOfProducts; i++) {
			// Generate Attributes
			int ID = generateID();
			String type = TYPES[random.nextInt(TYPES.length)];
			double price = generatePrice();
			String name = generateName(type);
			int quantity = generateQuantity();
			String status = STATUS[random.nextInt(STATUS.length)];
			String size = SIZES[random.nextInt(SIZES.length)];
			String imageURL = generateImageURL(type, name);

			// Create & Initialize Product Object
			Product product = new Product(ID, type, price, name, quantity, status, size, imageURL);
			listOfProducts.add(product);
			// System.out.println(product.toString());
		}
	}

	// Generate Random ID
	public int generateID() {
		int minID = 10000;
		int maxID = 99999;

		return random.nextInt(maxID - minID + 1) + minID;
	}

	// Generate Random Price
	public double generatePrice() {
		int minPrice = 100;
		int maxPrice = 500;
		double price = random.nextInt(maxPrice - minPrice + 1) + minPrice;

		return price;
	}

	// Generate Name based on Type
	public String generateName(String type) {
		String name = "";
		if ("FOOD".equalsIgnoreCase(type)) {
			name = FOOD_NAMES[random.nextInt(FOOD_NAMES.length)];
		} else if ("TOY".equalsIgnoreCase(type)) {
			name = TOY_NAMES[random.nextInt(TOY_NAMES.length)];
		} else if ("ACCESSORY".equalsIgnoreCase(type)) {
			name = ACCESSORY_NAMES[random.nextInt(ACCESSORY_NAMES.length)];
		}
		return name;
	}

	// Generate Random Quantity
	public int generateQuantity() {
		Random random = new Random();
		int quantity = random.nextInt(30) + 1;
		return quantity;
	}

	// Generate ImageURL based on Type and Name
	public String generateImageURL(String type, String name) {
		switch (type) {
		case "FOOD":
			return FOOD_IMAGES[random.nextInt(FOOD_IMAGES.length)];
		case "TOY":
			return TOY_IMAGES.getOrDefault(name, "unknown.png");
		case "ACCESSORY":
			return ACCESSORY_IMAGES.getOrDefault(name, "unknown.png");
		default:
			return "unknown.png";
		}
	}

	// Get Product List
	public ArrayList<Product> getProductList() {
		return listOfProducts;
	}

}