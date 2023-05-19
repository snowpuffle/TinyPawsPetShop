package models.animals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AnimalDAO {
	private final Connection connection;

	// SQL Queries
	private static final String GET_ALL_ANIMALS = "SELECT * FROM Animals";
	private static final String GET_ANIMAL_BY_ID = "SELECT * FROM Animals WHERE ID = ?";
	private static final String ADD_ANIMAL = "INSERT INTO Animals (ID, Name, Type, Price, DateOfBirth, Breed, Gender, Status, ImageURL) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
	private static final String UPDATE_ANIMAL = "UPDATE Animals SET Name = ?, Price = ?, Status = ? WHERE ID = ?";
	private static final String DELETE_ANIMAL = "DELETE FROM Animals WHERE ID = ?";

	// Default Class Constructor
	public AnimalDAO(Connection connection) {
		this.connection = connection;
	}

	// Get All Animals from Database
	public List<Animal> getAllAnimals() {
		// Initialize Empty Animal List
		List<Animal> animals = new ArrayList<>();

		// Initialize SQL Components
		PreparedStatement statement = null;
		ResultSet results = null;

		// Attempt to Get All Animals from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(GET_ALL_ANIMALS);

			// Execute Statement and Handle Results
			results = statement.executeQuery();
			while (results.next()) {
				Animal animal = addAnimalFromResultSet(results);
				animals.add(animal);
			}
		} catch (SQLException e) {
			System.out.println("ERROR ANIMALDAO: Cannot Get Animals! " + e);
		}

		// Return List of Animals
		return animals;
	}

	// Get Animal By ID From Database
	public Animal getAnimalByID(int ID) {
		// Initialize Empty Animal & SQL Components
		Animal animal = null;
		PreparedStatement statement = null;
		ResultSet results = null;

		// Attempt to Get Animal from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(GET_ANIMAL_BY_ID);
			statement.setInt(1, ID);
			// Execute Statement and Handle Results
			results = statement.executeQuery();
			if (results.next()) {
				animal = addAnimalFromResultSet(results);
			}
		} catch (SQLException e) {
			System.out.println("ERROR ANIMALDAO: Cannot Get Animal by ID! " + e);
		}

		// Return Animal
		return animal;
	}

	// Get Animal From Result Set
	private Animal addAnimalFromResultSet(ResultSet results) {
		// Initialize Empty Animal
		Animal animal = null;

		// Attempt to Extract Animal Attributes from ResultSet
		try {
			int id = results.getInt("ID");
			String name = results.getString("Name");
			String type = results.getString("Type");
			double price = results.getDouble("Price");
			String dateOfBirth = results.getString("DateOfBirth");
			String breed = results.getString("Breed");
			String gender = results.getString("Gender");
			String status = results.getString("Status");
			String imageURL = results.getString("ImageURL");

			// Create a New animal Object
			animal = new Animal(id, type, price, name, dateOfBirth, breed, gender, status, imageURL);

		} catch (SQLException e) {
			System.out.println("ERROR ANIMALDAO: Cannot Extract Animal Attributes from ResultSet! " + e);
		}
		// Return Animal
		return animal;
	}

	// Add Animal to Database
	public boolean addAnimal(Animal animal) {
		// Initialize Flag & SQL Component
		boolean success = true;
		PreparedStatement statement = null;

		// Attempt to Add Animal to Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(ADD_ANIMAL);
			statement.setInt(1, animal.getID());
			statement.setString(2, animal.getName());
			statement.setString(3, animal.getType());
			statement.setDouble(4, animal.getPrice());
			statement.setString(5, animal.getDateOfBirth());
			statement.setString(6, animal.getBreed());
			statement.setString(7, animal.getGender());
			statement.setString(8, animal.getStatus());
			statement.setString(9, animal.getImageURL());
			// Execute Statement
			statement.executeUpdate();
		} catch (SQLException e) {
			success = false;
			System.out.println("ERROR ANIMALDAO: Cannot Add Animal! " + e);
		}
		// Return Flag
		return success;
	}

	// Update Animal from Database
	public boolean editAnimal(Animal animal) {
		// Initialize Flag & SQL Component
		boolean success = false;
		PreparedStatement statement = null;

		// Attempt to Update Animal from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(UPDATE_ANIMAL);
			statement.setString(1, animal.getName());
			statement.setDouble(2, animal.getPrice());
			statement.setString(3, animal.getStatus());
			statement.setInt(4, animal.getID());
			// Execute Statement and Handle Results
			int rowsUpdated = statement.executeUpdate();
			if (rowsUpdated > 0) {
				success = true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR ANIMALDAO: Cannot Update Animal! " + e);
		}
		// Return Flag
		return success;
	}

	// Delete Animal from Database
	public boolean deleteAnimal(Animal animal) {
		// Initialize Flag & SQL Component
		boolean success = false;
		PreparedStatement statement = null;

		// Attempt to Delete Animal from Database
		try {
			// Prepare Statement and Set Values
			statement = connection.prepareStatement(DELETE_ANIMAL);
			statement.setInt(1, animal.getID());
			// Execute Statement and Handle Results
			int rowsDeleted = statement.executeUpdate();
			if (rowsDeleted > 0) {
				success = true;
			}
		} catch (SQLException e) {
			System.out.println("ERROR ANIMALDAO: Cannot Delete Animal! " + e);
		}
		// Return Flag
		return success;
	}
}