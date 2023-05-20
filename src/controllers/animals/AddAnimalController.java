package controllers.animals;

import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Model;
import models.animals.Animal;

public class AddAnimalController implements Initializable {
	// Main Attributes
	public TextField NameField;
	public TextField DateOfBirthField;
	public TextField PriceField;
	public TextField BreedField;
	public TextField ImageField;
	public ComboBox<String> StatusField;
	public ComboBox<String> TypeField;
	public ComboBox<String> GenderField;

	// Utility Attributes
	public Button SubmitButton;
	public Button GoBackButton;
	public Label MessageLabel;

	@Override
	// Initialize Method
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize Frame
		initializeFrame();

		// Initialize OnClick Action of Submit Button
		SubmitButton.setOnAction(event -> handleAddAnimal());
		GoBackButton.setOnAction(event -> handleGoBack());
	}

	// Initialize Frame
	private void initializeFrame() {
		// Initialize ComboBox Fields
		StatusField.getItems().addAll("Available", "Sold", "Pending");
		TypeField.getItems().addAll("Dog", "Cat");
		GenderField.getItems().addAll("Male", "Female");
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: "Submit" Button is Clicked
	public void handleAddAnimal() {
		// Get TextField Values
		String name = NameField.getText();
		String dob = DateOfBirthField.getText();
		String price = PriceField.getText();
		String status = StatusField.getValue();
		String type = TypeField.getValue();
		String breed = BreedField.getText();
		String gender = GenderField.getValue();
		String imageURL = ImageField.getText();

		// Validate the Fields
		if (validateFields(type, price, name, dob, gender, status, imageURL, breed)) {
			// Add Animal ONLY if Fields are Valid
			addAnimal(type, price, name, dob, breed, gender, status, imageURL);
		}
	}

	// Add Animal to Database using Model
	private void addAnimal(String type, String price, String name, String dateOfBirth, String breed, String gender,
			String status, String imageURL) {

		// Generate Attributes for Animal
		double priceDouble = Double.parseDouble(price);
		int ID = generateID();

		// Attempt to Add Animal to Database
		try {
			// Add Animal to Database by Accessing the Model
			Animal animal = new Animal(ID, type, priceDouble, name, dateOfBirth, breed, gender, status, imageURL);
			Model.getInstance().addNewAnimal(animal);

			// Reset the Form and Display Success Message
			resetFrame();
			handleMessageLabel("Animal Added to Database!", true);
		} catch (SQLException e) {
			// e.printStackTrace();
			handleMessageLabel("Cannot Add Animal to Database!", false);
		}
	}

	// Validate All Input Fields
	private boolean validateFields(String type, String price, String name, String dob, String gender, String status,
			String imageURL, String breed) {
		// Validate All Fields
		if (!validateName(name)) {
			return false;
		} else if (!validateDOB(dob)) {
			return false;
		} else if (!validatePrice(price)) {
			return false;
		} else if (!validateStatus(status)) {
			return false;
		} else if (!validateType(type)) {
			return false;
		} else if (!validateBreed(breed)) {
			return false;
		} else if (!validateGender(gender)) {
			return false;
		} else if (!validateImageURL(imageURL)) {
			return false;
		}

		// Return Flag
		return true;
	}

	// Validate Name Field
	private boolean validateName(String name) {
		// Check if Name Field is Empty
		if (name.isBlank() || name.isEmpty()) {
			handleMessageLabel("Please Enter a Name!", false);
			return false;
		}
		return true;
	}

	// Validate Date of Birth Field
	private boolean validateDOB(String dateOfBirth) {
		// Validate DOB is Valid and NOT Empty
		try {
			// Check if DOB Field is Empty
			if (dateOfBirth.isBlank() || dateOfBirth.isEmpty()) {
				handleMessageLabel("Please Enter Date of Birth!", false);
				return false;
			} else {
				// Validate Date Type and Format
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateFormat.setLenient(false);
				dateFormat.parse(dateOfBirth.trim());
				return true;
			}
		} catch (ParseException pe) {
			handleMessageLabel("Please Enter a Valid Date of Birth!", false);
			return false;
		}
	}

	// Validate Price Field
	private boolean validatePrice(String price) {
		// Validate Price is Valid and NOT Empty
		try {
			// Check if Price Field is Empty
			if (price.isBlank() || price.isEmpty()) {
				handleMessageLabel("Please Enter a Sale Price!", false);
				return false;
			} else {
				// Validate Price Type
				Double.parseDouble(price);
				return true;
			}
		} catch (NumberFormatException error) {
			handleMessageLabel("Please Enter a Valid Price!", false);
			return false;
		}
	}

	// Validate Status Field
	private boolean validateStatus(String status) {
		// Check if Status Field is Empty
		if (status == null || status.isBlank()) {
			handleMessageLabel("Please Select a Status!", false);
			return false;
		}
		return true;
	}

	// Validate Type Field
	private boolean validateType(String type) {
		// Check if Type Field is Empty
		if (type == null || type.isBlank()) {
			handleMessageLabel("Please Select a Type!", false);
			return false;
		}
		return true;
	}

	// Validate Breed Field
	private boolean validateBreed(String breed) {
		// Check if Breed Field is Empty
		if (breed.isBlank() || breed.isEmpty()) {
			handleMessageLabel("Please Enter a Breed!", false);
			return false;
		}
		return true;
	}

	// Validate Gender Field
	private boolean validateGender(String gender) {
		// Check if Gender Field is Empty
		if (gender == null || gender.isBlank()) {
			handleMessageLabel("Please Select a Gender!", false);
			return false;
		}
		return true;
	}

	// Validate ImageURL Field
	private boolean validateImageURL(String imageURL) {
		// Check if ImageURL Field is Empty
		if (imageURL.isBlank() || imageURL.isEmpty()) {
			handleMessageLabel("Please Enter an ImageURL!", false);
			return false;
		}
		return true;
	}

	// Generate Random ID Integer
	public int generateID() {
		Random random = new Random();
		int minID = 10000;
		int maxID = 99999;
		return random.nextInt(maxID - minID + 1) + minID;
	}

	// Clear & Reset All Fields on Frame
	private void resetFrame() {
		NameField.clear();
		DateOfBirthField.clear();
		PriceField.clear();
		StatusField.getSelectionModel().clearSelection();
		TypeField.getSelectionModel().clearSelection();
		BreedField.clear();
		GenderField.getSelectionModel().clearSelection();
		ImageField.clear();
		MessageLabel.setText("");
	}

	// Handle Message Label
	private void handleMessageLabel(String message, boolean success) {
		if (success) {
			MessageLabel.setText("SUCCESS: " + message);
			MessageLabel.setTextFill(Color.GREEN); // Set Text Color to Green for Success
		} else {
			MessageLabel.setText("ERROR: " + message);
			MessageLabel.setTextFill(Color.RED); // Set Text Color to Red for Error
		}
	}

	// Generic: Close Current Window
	private void closeCurrentWindow() {
		// Get & Close the Current Window
		Stage stage = (Stage) GoBackButton.getScene().getWindow();
		Model.getInstance().getViewFactory().closeStage(stage);
	}
}