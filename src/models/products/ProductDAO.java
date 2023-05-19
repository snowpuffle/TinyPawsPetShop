package models.products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
	private final Connection connection;

	// SQL Queries
	private static final String GET_ALL_PRODUCTS = "SELECT * FROM Products";
	private static final String GET_PRODUCT_BY_ID = "SELECT * FROM Products WHERE ID = ?";
	private static final String ADD_PRODUCT = "INSERT INTO Products (ID, Name, Type, Price, Quantity, Status, Size, ImageURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_PRODUCT = "UPDATE Products SET Quantity = ?, Price = ?, Status = ? WHERE ID = ?";
	private static final String DELETE_PRODUCT = "DELETE FROM Products WHERE ID = ?";

	// Default Class Constructor
	public ProductDAO(Connection connection) {
		this.connection = connection;
	}

	// Get All Products from Database
	public List<Product> getAllProducts() {
		// Initialize Empty Product List
		List<Product> products = new ArrayList<>();

		// Initialize SQL Components
		PreparedStatement statement = null;
		ResultSet results = null;

		// Attempt to Get All Animals from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(GET_ALL_PRODUCTS);

			// Execute Statement and Handle Results
			results = statement.executeQuery();
			while (results.next()) {
				Product product = addProductFromResultSet(results);
				products.add(product);
			}
		} catch (SQLException e) {
			System.out.println("ERROR ANIMALDAO: Cannot Get Products! " + e);
		}

		// Return List of Products
		return products;
	}

	// Get Product By ID From Database
	public Product getProductByID(int ID) {
		// Initialize Empty Product & SQL Components
		Product product = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		// Attempt to Get Product from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(GET_PRODUCT_BY_ID);
			statement.setInt(1, ID);
			// Execute Statement and Handle Results
			results = statement.executeQuery();
			if (results.next()) {
				product = addProductFromResultSet(results);
			}
		} catch (SQLException e) {
			System.out.println("ERROR PRODUCTDAO: Cannot Get Product by ID! " + e);
		}

		// Return Product
		return product;
	}

	// Get Product From Result Set
	private Product addProductFromResultSet(ResultSet results) {
		// Initialize Empty Product
		Product product = null;

		// Attempt to Extract Product Attributes from ResultSet
		try {
			int ID = results.getInt("ID");
			String name = results.getString("Name");
			String type = results.getString("Type");
			double price = results.getDouble("Price");
			int quantity = results.getInt("Quantity");
			String status = results.getString("Status");
			String size = results.getString("Size");
			String imageURL = results.getString("ImageURL");

			// Create a New Product Object
			product = new Product(ID, type, price, name, quantity, status, size, imageURL);

		} catch (SQLException e) {
			System.out.println("ERROR PRODUCTDAO: Cannot Extract Product Attributes from ResultSet! " + e);
		}

		// Return Product
		return product;
	}

	// Add Product to Database
	public boolean addProduct(Product product) {
		// Initialize Flag & SQL Component
		boolean success = true;
		PreparedStatement statement = null;

		// Attempt to Add Product to Database
		try {
			// Prepare Statement with the SQL Query
			statement = connection.prepareStatement(ADD_PRODUCT);
			statement.setInt(1, product.getID());
			statement.setString(2, product.getName());
			statement.setString(3, product.getType());
			statement.setDouble(4, product.getPrice());
			statement.setInt(5, product.getQuantity());
			statement.setString(6, product.getStatus());
			statement.setString(7, product.getSize());
			statement.setString(8, product.getImageURL());
			// Execute Statement
			statement.executeUpdate();
		} catch (SQLException e) {
			success = false;
			System.out.println("ERROR PRODUCTDAO: Cannot Add Product! " + e);
		}
		// Return Flag
		return success;
	}

	// Update Product from Database
	public boolean editProduct(Product product) {
		// Initialize Flag & SQL Component
		boolean success = false;
		PreparedStatement statement = null;

		// Attempt to Update Product from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(UPDATE_PRODUCT);
			statement.setInt(1, product.getQuantity());
			statement.setDouble(2, product.getPrice());
			statement.setString(3, product.getStatus());
			statement.setInt(4, product.getID());
			// Execute Statement and Handle Results
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				success = true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR PRODUCTDAO: Cannot Update Product!" + e);
		}

		// Return Flag
		return success;
	}

	// Delete Product from Database
	public boolean deleteProduct(Product product) {
		// Initialize Flag & SQL Component
		boolean success = false;
		PreparedStatement statement = null;

		// Attempt to Delete Product from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(DELETE_PRODUCT);
			statement.setInt(1, product.getID());
			// Execute Statement and Handle Results
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				success = true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR PRODUCTDAO: Cannot Delete Product! " + e);
		}
		// Return Flag
		return success;
	}

}