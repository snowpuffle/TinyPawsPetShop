package controllers.animals;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Model;
import models.animals.Animal;

public class ViewAnimalsController implements Initializable {
	// List of All Animals
	List<Animal> ListOfAnimals;

	// Attributes for Table of Animals
	public TableView<Animal> TableOfAnimals;
	public TableColumn<Animal, Integer> ColumnID;
	public TableColumn<Animal, String> ColumnName;
	public TableColumn<Animal, String> ColumnType;
	public TableColumn<Animal, String> ColumnDOB;
	public TableColumn<Animal, Double> ColumnPrice;

	// Attributes for Animal Card
	public Label IDField;
	public Label NameField;
	public ImageView ImageField;
	public Label TypeField;
	public Label DateOfBirthField;
	public Label PriceField;
	public Label BreedField;
	public Label GenderField;
	public Label StatusField;

	// Utility Attributes
	public Button GoBackButton;
	public Button EditAnimalButton;

	// Initialize Method
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeTable();
		addListeners();
	}

	// Initialize OnClick Actions for All Buttons
	private void addListeners() {
		// Set a Listener on Table View to Update Animal Data When a Row is Selected
		TableOfAnimals.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				updateAnimalCard(newSelection);
			}
		});

		// Initialize OnClick Action of Submit Button
		EditAnimalButton.setOnAction(event -> handleEditAnimal());
		GoBackButton.setOnAction(event -> handleGoBack());
	}

	// Initialize Cell Values and Data for Table
	private void initializeTable() {
		ColumnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		ColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
		ColumnDOB.setCellValueFactory(new PropertyValueFactory<>("dateOfBirth"));
		ColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		displayAnimalData();
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: Handle Edit Animal Button
	private void handleEditAnimal() {
		Animal selectedAnimal = TableOfAnimals.getSelectionModel().getSelectedItem();
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showEditAnimalFrame(selectedAnimal);
	}

	// Display Animal Data to Table
	public void displayAnimalData() {
		// Attempt to Display Animal Data to Table
		try {
			// Get List of Animals by Accessing the Model
			ListOfAnimals = Model.getInstance().getAllAnimals();
			// Send Data to Table
			ObservableList<Animal> data = FXCollections.observableList(ListOfAnimals);
			TableOfAnimals.setItems(data);
			// Select the First Row
			TableOfAnimals.getSelectionModel().selectFirst();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("ERROR CONTROLLER: Cannot Get List of Animals from the Database!");
		}
	}

	// Update Animal Card
	private void updateAnimalCard(Animal animal) {

		try {
			// No Animal Selected
			if (animal == null) {
				showErrorDialog("ERROR: No Animal Selected!");
				return;
			}

			// Set Default Values to Prevent Errors
			String name = animal.getName() != null ? animal.getName() : "Unknown";
			String id = String.valueOf(animal.getID());
			String type = animal.getType() != null ? animal.getType() : "Unknown";
			String dob = animal.getDateOfBirth();
			String price = animal.getPrice() > 0 ? "$" + animal.getPrice() : "N/A";
			String breed = animal.getBreed() != null ? animal.getBreed() : "Unknown";
			String gender = animal.getGender() != null ? animal.getGender() : "Unknown";
			String status = animal.getStatus() != null ? animal.getStatus() : "Unknown";

			// Validate and Load Image
			String imageURL = fixImage(animal.getImageURL(), type);
			try {
				ImageField.setImage(new Image(new File(imageURL).toURI().toString()));
			} catch (IllegalArgumentException e) {
				showErrorDialog("ERROR: Invalid Image Path!");
				ImageField.setImage(
						new Image(new File(System.getProperty("user.dir") + "\\resources\\images\\icons\\warning.png")
								.toURI().toString()));
			}

			// Update UI fields
			NameField.setText(name);
			IDField.setText(id);
			ImageField.setImage(new Image(new File(imageURL).toURI().toString()));
			TypeField.setText(type);
			DateOfBirthField.setText(dob);
			PriceField.setText(price);
			BreedField.setText(breed);
			GenderField.setText(gender);
			StatusField.setText(status);

		} catch (Exception e) {
			showErrorDialog("ERROR: Occurred while Updating the Card!");
		}

		// Fix Image URL
		String type = animal.getType();
		String imageURL = fixImage(animal.getImageURL(), type);

		// Update Animal Card UI based on Selected Animal
		NameField.setText(animal.getName());
		IDField.setText(String.valueOf(animal.getID()));
		ImageField.setImage(new Image(new File(imageURL).toURI().toString()));
		TypeField.setText(type);
		DateOfBirthField.setText(animal.getDateOfBirth());
		PriceField.setText("$" + animal.getPrice());
		BreedField.setText(animal.getBreed());
		GenderField.setText(animal.getGender());
		StatusField.setText(animal.getStatus());
	}

	// Fix ImageURL Based on Type
	private String fixImage(String image, String type) {
		// Initialize Empty Location
		String mainLocation = System.getProperty("user.dir") + "\\resources\\images";
		String imageLocation = "";

		// Set Default Image if Null or Empty
		if (image == null || image.trim().isEmpty()) {
			return mainLocation + "\\icons\\warning.png";
		}

		// Set Image Folder Location based on Type
		if ("CAT".equalsIgnoreCase(type)) {
			imageLocation = mainLocation + "\\cats\\" + image;
		} else if ("DOG".equalsIgnoreCase(type)) {
			imageLocation = mainLocation + "\\dogs\\" + image;
		}

		// Check if File Exists
		File file = new File(imageLocation);
		if (!file.exists()) {
			imageLocation = mainLocation + "\\icons\\warning.png";
		}

		// Return Image Location
		return imageLocation;
	}

	// Display Error Messages
	private void showErrorDialog(String message) {
		System.out.println(message);
	}

	// Generic: Close Current Window
	private void closeCurrentWindow() {
		// Get & Close the Current Window
		Stage stage = (Stage) GoBackButton.getScene().getWindow();
		Model.getInstance().getViewFactory().closeStage(stage);
	}
}