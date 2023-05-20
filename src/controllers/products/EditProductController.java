package controllers.products;

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
import models.products.Product;

public class EditProductController implements Initializable {
	// Product for THIS Controller
	private Product product;

	// Main Attributes
	public TextField NameField;
	public TextField QuantityField;
	public TextField PriceField;
	public TextField ImageField;
	public ComboBox<String> StatusField;
	public ComboBox<String> TypeField;
	public ComboBox<String> SizeField;

	// Utility Attributes
	public Button SubmitButton;
	public Button GoBackButton;
	public Label MessageLabel;

	// Default Class Constructor
	public EditProductController(Product product) {
		this.product = product;
	}

	@Override
	// Initialize Method
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize Frame
		initializeFrame();

		// Initialize OnClick Action of Submit Button
		SubmitButton.setOnAction(event -> handleEditProduct());
		GoBackButton.setOnAction(event -> handleGoBack());
	}

	// Initialize Frame
	private void initializeFrame() {
		// Initialize & Disable Editing of Some Fields
		initializeFields();
		StatusField.getItems().addAll("Available", "Not Available");
		SizeField.setEditable(false);
		NameField.setEditable(false);
		TypeField.setEditable(false);
		ImageField.setEditable(false);
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: "Submit" Button is Clicked
	public void handleEditProduct() {
		// Get New TextField Values
		String newQuantity = QuantityField.getText();
		String newPrice = PriceField.getText();
		String newStatus = StatusField.getValue();

		// Validate the New Values
		if (validateFields(newPrice, newQuantity, newStatus)) {
			// Update Product ONLY if New Values are Valid
			editProduct(newPrice, newQuantity, newStatus);
		}
	}

	// Update Product to Database using Model
	private void editProduct(String newPrice, String newQuantity, String newStatus) {
		// Convert Doubles and Integers
		double priceDouble = Double.parseDouble(newPrice);
		int quantityInt = Integer.parseInt(newQuantity);

		// Set New Data to Product
		product.setQuantity(quantityInt);
		product.setStatus(newStatus);
		product.setPrice(priceDouble);

		// Edit Product to Database by Accessing the Model
		Model.getInstance().editProduct(product);

		// Reset the Form and Display Success Message
		handleMessageLabel("Product Updated to Database!", true);
	}

	// Initialize All Fields for Product
	public void initializeFields() {
		if (product != null) {
			NameField.setText(product.getName());
			QuantityField.setText(String.valueOf(product.getQuantity()));
			PriceField.setText(String.valueOf(product.getPrice()));
			StatusField.setValue(product.getStatus());
			TypeField.setValue(product.getType());
			SizeField.setValue(product.getSize());
			ImageField.setText(product.getImageURL());
		}
	}

	// Validate All Input Fields
	private boolean validateFields(String price, String quantity, String status) {
		// Validate All Fields
		if (!validateQuantity(quantity)) {
			return false;
		} else if (!validatePrice(price)) {
			return false;
		} else if (!validateStatus(status)) {
			return false;
		}
		// Return Flag
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

	// Validate Quantity Field
	private boolean validateQuantity(String quantity) {
		// Validate Quantity is Valid and NOT Empty
		try {
			// Check if Quantity Field is Empty
			if (quantity.isBlank() || quantity.isEmpty()) {
				handleMessageLabel("Please Enter a Quantity!", false);
				return false;
			} else {
				// Validate Quantity Type
				int parsedQuantity = Integer.parseInt(quantity);

				// Validate Quantity Value is Between 1-100
				if (parsedQuantity >= 1 && parsedQuantity <= 100) {
					return true;
				} else {
					handleMessageLabel("Please Enter a Quantity Between 1-100!", false);
					return false;
				}
			}
		} catch (NumberFormatException e) {
			handleMessageLabel("Please Enter a Valid Quantity!", false);
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