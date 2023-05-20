module TinyPawsPetShop {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	requires javafx.graphics;

	opens petshopsystem to javafx.graphics, javafx.fxml, javafx.base;
	opens controllers to javafx.graphics, javafx.fxml, javafx.base;
	opens controllers.animals to javafx.graphics, javafx.fxml, javafx.base;
	// opens controllers.products to javafx.graphics, javafx.fxml, javafx.base;
	opens models to javafx.graphics, javafx.fxml, javafx.base;
	opens models.animals to javafx.graphics, javafx.fxml, javafx.base;
	opens models.products to javafx.graphics, javafx.fxml, javafx.base;
	opens models.users to javafx.graphics, javafx.fxml, javafx.base;
}
