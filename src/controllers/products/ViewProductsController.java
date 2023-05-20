package controllers.products;

import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import models.Model;
import models.products.Product;

public class ViewProductsController implements Initializable {
	// List of All Products
	List<Product> ListOfProducts;

	// Attributes for Table of Products
	public TableView<Product> TableOfProducts;
	public TableColumn<Product, Integer> ColumnID;
	public TableColumn<Product, String> ColumnName;
	public TableColumn<Product, String> ColumnType;
	public TableColumn<Product, String> ColumnQuantity;
	public TableColumn<Product, Double> ColumnPrice;

	// Attributes for Product Card
	public Label IDField;
	public Label NameField;
	public ImageView ImageField;
	public Label TypeField;
	public Label QuantityField;
	public Label PriceField;
	public Label SizeField;
	public Label StatusField;

	// Utility Attributes
	public Button GoBackButton;
	public Button EditProductButton;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		initializeTable();
		addListeners();
	}

	// Initialize OnClick Actions for All Buttons
	private void addListeners() {
		// Set a Listener on Table View to Update Animal Data When a Row is Selected
		TableOfProducts.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
			if (newSelection != null) {
				updateProductCard(newSelection);
			}
		});

		// Initialize OnClick Action of Submit Button
		EditProductButton.setOnAction(event -> handleEditProduct());
		GoBackButton.setOnAction(event -> handleGoBack());
	}

	// Initialize Cell Value for Table
	private void initializeTable() {
		ColumnID.setCellValueFactory(new PropertyValueFactory<>("ID"));
		ColumnName.setCellValueFactory(new PropertyValueFactory<>("name"));
		ColumnType.setCellValueFactory(new PropertyValueFactory<>("type"));
		ColumnQuantity.setCellValueFactory(new PropertyValueFactory<>("quantity"));
		ColumnPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
		displayProductData();
	}

	// Event: "Go Back" Button is Clicked
	private void handleGoBack() {
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showDashboardFrame();
	}

	// Event: Handle Edit Product Button
	private void handleEditProduct() {
		Product selectedProduct = TableOfProducts.getSelectionModel().getSelectedItem();
		closeCurrentWindow();
		Model.getInstance().getViewFactory().showEditProductFrame(selectedProduct);
	}

	// Display Product Data to Table
	public void displayProductData() {
		// Attempt to Display Product Data to Table
		try {
			// Get List of Products by Accessing the Model
			ListOfProducts = Model.getInstance().getAllProducts();
			// Send Data to Table
			ObservableList<Product> data = FXCollections.observableList(ListOfProducts);
			TableOfProducts.setItems(data);
			// Select the First Row
			TableOfProducts.getSelectionModel().selectFirst();
		} catch (Exception e) {
			// e.printStackTrace();
			System.out.println("ERROR CONTROLLER: Cannot Get List of Products from the Database!");
		}
	}

	// Update Product Card
	private void updateProductCard(Product product) {
		// Fix Image URL
		String type = product.getType();
		String imageURL = fixImage(product.getImageURL(), type);

		// Update Product Card UI based on Selected Product
		NameField.setText(product.getName());
		IDField.setText(String.valueOf(product.getID()));
		ImageField.setImage(new Image(imageURL));
		TypeField.setText(type);
		QuantityField.setText(String.valueOf(product.getQuantity()));
		PriceField.setText("$" + product.getPrice());
		SizeField.setText(product.getSize());
		StatusField.setText(product.getStatus());
	}

	// Fix ImageURL Based on Type
	private String fixImage(String image, String type) {
		// Initialize Empty Location
		String mainLocation = System.getProperty("user.dir") + "\\resources\\images\\";
		String imageLocation = "";

		// Set Image Folder Location based on Type
		if ("FOOD".equalsIgnoreCase(type)) {
			imageLocation = mainLocation + "\\food\\" + image;
		} else if ("TOY".equalsIgnoreCase(type)) {
			imageLocation = mainLocation + "\\toys\\" + image;
		} else if ("ACCESSORY".equalsIgnoreCase(type)) {
			imageLocation = mainLocation + "\\accessories\\" + image;
		}

		// Check if File Exists
		File file = new File(imageLocation);
		if (!file.exists()) {
			imageLocation = mainLocation + "\\icons\\warning.png";
		}

		// Return Image Location
		return imageLocation;
	}

	// Generic: Close Current Window
	private void closeCurrentWindow() {
		// Get & Close the Current Window
		Stage stage = (Stage) GoBackButton.getScene().getWindow();
		Model.getInstance().getViewFactory().closeStage(stage);
	}
}