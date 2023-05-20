package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import models.Model;

public class DashboardController implements Initializable {
	// Buttons for Animals
	public Button ViewAnimalsButton;
	public Button AddAnimalButton;
	public Button SearchAnimalButton;

	// Buttons for Products
	public Button ViewProductsButton;
	public Button AddProductButton;
	public Button SearchProductButton;

	// Utility Attributes
	public Button LogoutButton;

	@Override
	// Initialize Method
	public void initialize(URL arg0, ResourceBundle arg1) {
		addListeners();
	}

	// Initialize OnClick Actions for All Buttons
	private void addListeners() {
		ViewAnimalsButton.setOnAction(event -> handleViewAnimals());
		AddAnimalButton.setOnAction(event -> handleAddAnimal());
		SearchAnimalButton.setOnAction(event -> handleSearchAnimal());
		ViewProductsButton.setOnAction(event -> handleViewProducts());
		AddProductButton.setOnAction(event -> handleAddProduct());
		SearchProductButton.setOnAction(event -> handleSearchProduct());
		LogoutButton.setOnAction(event -> handleLogout());
	}

	// Event: "View All Animals" Button is Clicked
	private void handleViewAnimals() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showViewAnimalsFrame();
	}

	// Event: "Add New Animal" Button is Clicked
	private void handleAddAnimal() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showAddAnimalFrame();
	}

	// Event: "Search for Animal" Button is Clicked
	private void handleSearchAnimal() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showSearchAnimalFrame();
	}

	// Event: "View All Products" Button is Clicked
	private void handleViewProducts() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showViewProductsFrame();
	}

	// Event: "Add New Product" Button is Clicked
	private void handleAddProduct() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showAddProductFrame();
	}

	// Event: "Search for Product" Button is Clicked
	private void handleSearchProduct() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showSearchProductFrame();
	}

	// Event: "Logout" Button is Clicked
	private void handleLogout() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showLoginFrame();
	}

	// Generic: Close Current Window
	private void closeCurrentWindow() {
		// Get & Close the Current Window
		Stage stage = (Stage) LogoutButton.getScene().getWindow();
		Model.getInstance().getViewFactory().closeStage(stage);
	}
}