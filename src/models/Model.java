package models;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import models.animals.*;
import models.products.*;
import models.users.*;
import petshopsystem.*;
import views.*;

public class Model {
	private static Model model;
	private final ViewFactory viewFactory;

	private AnimalDAO animalDAO;
	private ProductDAO productDAO;
	private UserDAO userDAO;

	// Default Class Constructor
	private Model() {
		// Set View Factory and DAOs
		this.viewFactory = new ViewFactory();
		this.animalDAO = new AnimalDAO(DBManager.getInstance().getConnection());
		this.productDAO = new ProductDAO(DBManager.getInstance().getConnection());
		this.userDAO = new UserDAO(DBManager.getInstance().getConnection());

		seedAnimals();
		seedProducts();
		seedUsers();
	}

	// Get Database Instance
	public static synchronized Model getInstance() {
		if (model == null) {
			model = new Model();
		}
		return model;
	}

	// Generate and Seed Animals to Database
	private void seedAnimals() {
		// Instantiate an AnimalSeeds Object to Generate the Animal Data
		AnimalSeeds seeds = new AnimalSeeds();

		// Generate Seed Animals
		seeds.generateAnimals(20);

		// Retrieve the List of Animals from the AnimalSeeds Object
		List<Animal> listOfAnimals = seeds.getAnimalList();

		// Iterate Over the List of Animals and Add Each Animal to the Database
		for (Animal animal : listOfAnimals) {
			animalDAO.addAnimal(animal);
		}
	}

	// Generate and Seed Animals to Database
	private void seedProducts() {
		// Instantiate an AnimalSeeds Object to Generate the Animal Data
		ProductSeeds seeds = new ProductSeeds();

		// Generate Seed Animals
		seeds.generateProducts(20);

		// Retrieve the List of Animals from the AnimalSeeds Object
		List<Product> listOfProducts = seeds.getProductList();

		// Iterate Over the List of Animals and Add Each Animal to the Database
		for (Product product : listOfProducts) {
			productDAO.addProduct(product);
		}
	}

	// Generate and Seed Users to Database
	private void seedUsers() {
		// Instantiate an UserSeeds Object to Generate the User Data
		UserSeeds seeds = new UserSeeds();

		// Generate Seed Users
		seeds.generateUsers();

		// Retrieve the List of Users from the UserSeeds Object
		List<User> listOfUsers = seeds.getUserList();

		// Iterate Over the List of Animals and Add Each Animal to the Database
		for (User user : listOfUsers) {
			userDAO.addUser(user);
		}
	}

	// Login User
	public User loginUser(String username, String password) {
		return userDAO.findByUserNameAndPassword(username, password);
	}

	// Get All Animals from Database
	public List<Animal> getAllAnimals() {
		return animalDAO.getAllAnimals();
	}

	// Get All Animals from Database
	public List<Product> getAllProducts() {
		return productDAO.getAllProducts();
	}

	// Get All Users from Database
	public List<User> getAllUsers() {
		return userDAO.getAllUsers();
	}

	// Add New Animal to Database
	public boolean addNewAnimal(Animal animal) throws SQLException {
		return animalDAO.addAnimal(animal);
	}

	// Add New Product to Database
	public boolean addNewProduct(Product product) throws SQLException {
		return productDAO.addProduct(product);
	}

	// Edit/Update Animal from Database
	public boolean editAnimal(Animal animal) {
		return animalDAO.editAnimal(animal);
	}

	// Edit/Update Product from Database
	public boolean editProduct(Product product) {
		return productDAO.editProduct(product);
	}

	// Get Animal by ID from Database
	public Animal getAnimalByID(int ID) {
		return animalDAO.getAnimalByID(ID);
	}

	// Get Product by ID from Database
	public Product getProductByID(int ID) {
		return productDAO.getProductByID(ID);
	}

	// Print All Animals
	public void printAllAnimals() {
		List<Animal> listOfAnimals = getAllAnimals();
		for (Animal animal : listOfAnimals) {
			System.out.println(animal.toString());
		}
	}

	// Print All Products
	public void printAllProducts() {
		List<Product> listOfProducts = getAllProducts();
		for (Product product : listOfProducts) {
			System.out.println(product.toString());
		}
	}

	// Print All Users
	public void printAllUsers() {
		List<User> listOfUsers = getAllUsers();
		for (User user : listOfUsers) {
			System.out.println(user.toString());
		}
	}

	// Get View Factory
	public ViewFactory getViewFactory() {
		return viewFactory;
	}

	// Generate Random ID Integer
	public int generateID() {
		Random random = new Random();
		int minID = 10000;
		int maxID = 99999;
		return random.nextInt(maxID - minID + 1) + minID;
	}
}