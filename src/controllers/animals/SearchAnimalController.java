package controllers.animals;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Model;
import models.animals.Animal;

public class SearchAnimalController implements Initializable {
	// Main Attributes
	public TextField IDField;

	// Utility Attributes
	public Label MessageLabel;
	public Button GoBackButton;
	public Button SubmitButton;

	// Initialize Method
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		addListeners();
	}

	// Initialize OnClick Actions for All Buttons
	private void addListeners() {
		GoBackButton.setOnAction(event -> handleGoBack());
		SubmitButton.setOnAction(event -> handleSearchAnimal());
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: "Submit" Button is Clicked
	private void handleSearchAnimal() {
		// Get ID TextField Value
		String stringID = IDField.getText();

		// Validate String ID
		if (stringID != null && validateID(stringID) != -1) {
			// Validate & Convert ID to Integer
			int ID = validateID(stringID);

			// Get Animal by ID & Display its Frame
			Animal animal = getAnimal(ID);
			if (animal != null) {
				closeCurrentWindow();
				Model.getInstance().getViewFactory().showEditAnimalFrame(animal);
			} else {
				handleMessageLabel("Cannot Find Animal with ID #" + ID + "!");
			}
		} else {
			handleMessageLabel("Please Enter a Valid ID - Must be a 5-Digit Number!");
		}
	}

	// Search Animal by ID from Database using Model
	private Animal getAnimal(int ID) {
		return Model.getInstance().getAnimalByID(ID);
	}

	// Validate Animal ID
	private int validateID(String stringID) {
		// Try to Convert ID to a 5 Digit Integer
		int formattedID = -1;
		try {
			// Check ID is an Integer
			int ID = Integer.parseInt(stringID);
			formattedID = Integer.parseInt(String.format("%05d", ID));

			// Check ID is 5-Digit
			if (String.valueOf(formattedID).length() != 5) {
				formattedID = -1;
			}
		} catch (NumberFormatException e) {
			formattedID = -1;
		}

		// Return Formatted ID
		return formattedID;
	}

	// Handle Error
	private void handleMessageLabel(String message) {
		MessageLabel.setText("ERROR: " + message);
		MessageLabel.setTextFill(Color.RED); // Set Text Color to Red for Error
	}

	// Generic: Close Current Window
	private void closeCurrentWindow() {
		// Get & Close the Current Window
		Stage stage = (Stage) GoBackButton.getScene().getWindow();
		Model.getInstance().getViewFactory().closeStage(stage);
	}
}