package views;

import controllers.*;
import controllers.animals.*;
import controllers.products.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.animals.Animal;
import models.products.Product;

public class ViewFactory {

	// Show User Login Window
	public void showLoginFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/Login.fxml"));
		LoginController LoginController = new LoginController();
		fxmlLoader.setController(LoginController);
		createStage(fxmlLoader, "User Login");
	}

	// Show Dashboard Window
	public void showDashboardFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/Dashboard.fxml"));
		DashboardController DashboardController = new DashboardController();
		fxmlLoader.setController(DashboardController);
		createStage(fxmlLoader, "Main Dashboard");
	}

	// Show View Animals Window
	public void showViewAnimalsFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/animals/ViewAnimals.fxml"));
		ViewAnimalsController ViewAnimalsController = new ViewAnimalsController();
		fxmlLoader.setController(ViewAnimalsController);
		createStage(fxmlLoader, "View All Animals");
	}

	// Show Add Animal Window
	public void showAddAnimalFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/animals/AddAnimal.fxml"));
		AddAnimalController AddAnimalController = new AddAnimalController();
		fxmlLoader.setController(AddAnimalController);
		createStage(fxmlLoader, "Add New Animal");
	}

	// Show Search Animal Window
	public void showSearchAnimalFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/animals/SearchAnimal.fxml"));
		SearchAnimalController SearchAnimalController = new SearchAnimalController();
		fxmlLoader.setController(SearchAnimalController);
		createStage(fxmlLoader, "Search Animal");
	}

	// Show Edit Animal Window
	public void showEditAnimalFrame(Animal animal) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/animals/EditAnimal.fxml"));
		EditAnimalController EditAnimalController = new EditAnimalController(animal);
		fxmlLoader.setController(EditAnimalController);
		createStage(fxmlLoader, "Edit Animal");
	}

	// Show View Products Window
	public void showViewProductsFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/products/ViewProducts.fxml"));
		ViewProductsController ViewProductsController = new ViewProductsController();
		fxmlLoader.setController(ViewProductsController);
		createStage(fxmlLoader, "View All Products");
	}

	// Show Add Product Window
	public void showAddProductFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/products/AddProduct.fxml"));
		AddProductController AddProductController = new AddProductController();
		fxmlLoader.setController(AddProductController);
		createStage(fxmlLoader, "Add New Product");
	}

	// Show Search Product Window
	public void showSearchProductFrame() {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/products/SearchProduct.fxml"));
		SearchProductController SearchProductController = new SearchProductController();
		fxmlLoader.setController(SearchProductController);
		createStage(fxmlLoader, "Search Product");
	}

	// Show Edit Product Window
	public void showEditProductFrame(Product product) {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/FXMLs/products/EditProduct.fxml"));
		EditProductController EditProductController = new EditProductController(product);
		fxmlLoader.setController(EditProductController);
		createStage(fxmlLoader, "Edit Product");
	}

	// Generic: Create Stage
	private void createStage(FXMLLoader fxmlLoader, String stageTitle) {
		Scene scene = null;
		try {
			scene = new Scene(fxmlLoader.load());
			Stage stage = new Stage();
			stage.setScene(scene);
			stage.setResizable(false);
			stage.setTitle("Pet Shop System");
			stage.show();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("ERROR: Issues with Creating Stage " + stageTitle + "!");
		}
	}

	// Generic: Close Stage
	public void closeStage(Stage stage) {
		stage.close();
	}
}