package controllers.products;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import models.Model;
import models.products.Product;

public class SearchProductController implements Initializable {
	// Main Attributes
	public TextField IDField;

	// Utility Attributes
	public Label MessageLabel;
	public Button GoBackButton;
	public Button SubmitButton;

	@Override
	// Initialize Method
	public void initialize(URL arg0, ResourceBundle arg1) {
		addListeners();
	}

	// Initialize OnClick Actions for All Buttons
	private void addListeners() {
		GoBackButton.setOnAction(event -> handleGoBack());
		SubmitButton.setOnAction(event -> handleSearchProduct());
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: "Submit" Button is Clicked
	private void handleSearchProduct() {
		// Get ID TextField Value
		String stringID = IDField.getText();

		// Validate String ID
		if (stringID != null && validateID(stringID) != -1) {
			// Validate & Convert ID to Integer
			int ID = validateID(stringID);

			// Get Product by ID & Display its Frame
			Product product = getProduct(ID);
			if (product != null) {
				closeCurrentWindow();
				Model.getInstance().getViewFactory().showEditProductFrame(product);
			} else {
				handleMessageLabel("Cannot Find Product with ID #" + ID + "!");
			}
		} else {
			handleMessageLabel("Please Enter a Valid ID - Must be a 5-Digit Number!");
		}
	}

	// Search Product by ID from Database using Model
	private Product getProduct(int ID) {
		return Model.getInstance().getProductByID(ID);
	}

	// Validate Product ID
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

	// Handle Message Label
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