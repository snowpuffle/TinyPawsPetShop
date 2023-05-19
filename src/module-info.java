module TinyPawsPetShop {
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	
	opens petshopsystem to javafx.graphics, javafx.fxml;
}
