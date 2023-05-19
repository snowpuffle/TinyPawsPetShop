module TinyPawsPetShop {
	requires javafx.controls;
	requires javafx.fxml;
	
	opens petshopsystem to javafx.graphics, javafx.fxml;
}
