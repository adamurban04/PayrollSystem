package com.mycompany.payrollsystem.system.javafx;

import com.mycompany.payrollsystem.system.user.Admin;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;

public class AdminController {

    private final Admin admin = new Admin();

    @FXML
    private TextField nameField;

    @FXML
    private TextField idField;

    @FXML
    private TextField titleField;

    @FXML
    private TextField scalePointField;

    @FXML
    private TextField passwordField;

    // Method for handling the "Add Staff" button click
    @FXML
    public void handleAddStaff() {
        try {
            // Get values from the fields
            String name = nameField.getText().trim();
            String idText = idField.getText().trim();
            String title = titleField.getText().trim();
            String scalePointText = scalePointField.getText().trim();
            String password = passwordField.getText().trim();

            // Validate ID and ScalePoint as numbers
            if (idText.isEmpty() || scalePointText.isEmpty()) {
                showAlert("Invalid Input", "Please fill out all fields correctly.", AlertType.ERROR);
                return;
            }

            int id = Integer.parseInt(idText);
            int scalePoint = Integer.parseInt(scalePointText);

            // Simulate user input by redirecting System.in
            String simulatedInput = name + "\n" + idText + "\n" + title + "\n" + scalePointText + "\n" + password + "\n";
            System.setIn(new ByteArrayInputStream(simulatedInput.getBytes(StandardCharsets.UTF_8)));

            // Call the existing addStaff method in the Admin class
            admin.addStaff(); // This will now use the simulated inputs

            // Show success alert
            showAlert("Staff added successfully!", "The staff member has been added to the system.", AlertType.INFORMATION);
        } catch (NumberFormatException e) {
            showAlert("Invalid Input", "Please ensure all numeric fields are filled correctly.", AlertType.ERROR);
        }
    }

    // Helper method for showing alerts
    private void showAlert(String title, String content, AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.showAndWait();
    }

    public void handleViewStaff(ActionEvent actionEvent) {
    }
}