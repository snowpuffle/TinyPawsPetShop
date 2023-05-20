package controllers.animals;

import java.net.URL;
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

public class EditAnimalController implements Initializable {
	// Animal for THIS Controller
	private Animal animal;

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

	// Default Class Constructor
	public EditAnimalController(Animal animal) {
		this.animal = animal;
	}

	@Override
	// Initialize Method
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize Frame
		initializeFrame();

		// Initialize OnClick Action of Submit Button
		SubmitButton.setOnAction(event -> handleEditAnimal());
		GoBackButton.setOnAction(event -> handleGoBack());
	}

	// Initialize Frame
	private void initializeFrame() {
		initializeFields();
		StatusField.getItems().addAll("Available", "Sold", "Pending");
		DateOfBirthField.setEditable(false);
		TypeField.setEditable(false);
		BreedField.setEditable(false);
		GenderField.setEditable(false);
		ImageField.setEditable(false);
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: "Submit" Button is Clicked
	public void handleEditAnimal() {
		// Get New TextField Values
		String newName = NameField.getText();
		String newPrice = PriceField.getText();
		String newStatus = StatusField.getValue();

		// Validate the New Values
		if (validateFields(newPrice, newName, newStatus)) {
			// Update Animal ONLY if New Values are Valid
			editAnimal(newPrice, newName, newStatus);
		}
	}

	// Update Animal to Database using Model
	private void editAnimal(String newPrice, String newName, String newStatus) {
		// Convert Price to a Double
		double priceDouble = Double.parseDouble(newPrice);

		// Set New Data to Animal
		animal.setName(newName);
		animal.setPrice(priceDouble);
		animal.setStatus(newStatus);

		// Edit Animal to Database by Accessing the Model
		Model.getInstance().editAnimal(animal);

		// Reset the Form and Display Success Message
		handleMessageLabel("Animal Updated to Database!", true);
	}

	// Initialize All Fields for Animal
	public void initializeFields() {
		if (animal != null) {
			MessageLabel.setText("");
			NameField.setText(animal.getName());
			DateOfBirthField.setText(animal.getDateOfBirth());
			PriceField.setText(String.valueOf(animal.getPrice()));
			StatusField.setValue(animal.getStatus());
			TypeField.setValue(animal.getType());
			BreedField.setText(animal.getBreed());
			GenderField.setValue(animal.getGender());
			ImageField.setText(animal.getImageURL());
		}
	}

	// Validate All Input Fields
	private boolean validateFields(String price, String name, String status) {
		// Validate All Fields
		if (!validateName(name)) {
			return false;
		} else if (!validatePrice(price)) {
			return false;
		} else if (!validateStatus(status)) {
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