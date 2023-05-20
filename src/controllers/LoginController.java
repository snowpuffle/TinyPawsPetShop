package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.stage.Stage;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import models.Model;
import models.users.User;

public class LoginController implements Initializable {
	// Main Attributes
	public TextField UsernameField;
	public PasswordField PasswordField;

	// Utility Attributes
	public Button SubmitButton;
	public Label MessageLabel;

	@Override
	// Initialize Method
	public void initialize(URL arg0, ResourceBundle arg1) {
		// Initialize OnClick Action of Login Button
		SubmitButton.setOnAction(event -> handleLogin());
	}

	// Event: Login Submit Button is Pressed
	private void handleLogin() {
		// Get Login Fields
		String username = UsernameField.getText().trim();
		String password = PasswordField.getText().trim();

		// Valid Login Fields: Username and Password are NOT Empty
		if (validateFields(username, password)) {
			// Get User Login Data from Database by Accessing the Model
			User user = Model.getInstance().loginUser(username, password);

			// Login Success: Close Window & Display Dashboard
			if (user != null) {
				// Get & Close the Current Window
				Stage stage = (Stage) MessageLabel.getScene().getWindow();
				Model.getInstance().getViewFactory().closeStage(stage);
				// Get & Display Dashboard Frame
				Model.getInstance().getViewFactory().showDashboardFrame();
			} else {
				// Login Failed: Invalid Username or Password
				handleMessageLabel("ERROR: Invalid Username or Password Combination!");
			}
		}
	}

	// Validate Fields
	private boolean validateFields(String username, String password) {
		// Initialize Flag
		boolean success = true;

		// Check if Fields are Empty
		if (username.isBlank() || username.isEmpty()) {
			success = false;
			handleMessageLabel("ERROR: Please Enter Your Username!");
		} else if (password.isBlank() || password.isEmpty()) {
			success = false;
			handleMessageLabel("ERROR: Please Enter Your Password!");
		}

		// Return Flag
		return success;
	}

	// Handle Message Label
	private void handleMessageLabel(String message) {
		MessageLabel.setText(message);
	}
}
