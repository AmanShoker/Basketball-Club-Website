package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class RegistrationPane {
    private Stage primaryStage;

    public RegistrationPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Create Account");
        Pane registrationPane = createRegistrationPane();
        Scene scene = new Scene(registrationPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createRegistrationPane() {
        StackPane stackPane = new StackPane();
        Text title = new Text("Create Account");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(20, 0, 0, 0));
        stackPane.getChildren().add(title);
    
        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);
    
        Label usernameLabel = new Label("Username:");
        usernameLabel.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        usernameField.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(usernameField, 1, 0);
    
        Label passwordLabel = new Label("Password:");
        passwordLabel.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        passwordField.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(passwordField, 1, 1);
    
        Label reEnterPasswordLabel = new Label("Re-enter Password:");
        reEnterPasswordLabel.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(reEnterPasswordLabel, 0, 2);
        PasswordField reEnterPasswordField = new PasswordField();
        reEnterPasswordField.setPromptText("Re-enter your password");
        reEnterPasswordField.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(reEnterPasswordField, 1, 2);
    
        Button createAccountButton = new Button("Create Account");
        createAccountButton.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(createAccountButton, 1, 3);
    
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(backButton, 0, 3);
    
        backButton.setOnAction(e -> navigateToLoginPage());
    
        createAccountButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            String reEnteredPassword = reEnterPasswordField.getText();
            if (!username.isEmpty() && !password.isEmpty() && !reEnteredPassword.isEmpty()) {
                if (password.equals(reEnteredPassword)) {

                    Account account = new Account(username, password);

                    boolean success = FileManager.registerUser(account);
                    if (success) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setTitle("Account Created");
                        alert.setHeaderText(null);
                        alert.setContentText("Account created successfully!");
                        alert.showAndWait();
                        navigateToLoginPage();
                    } else {
                        Alert alert = new Alert(Alert.AlertType.ERROR);
                        alert.setTitle("Error");
                        alert.setHeaderText(null);
                        alert.setContentText("Username already exists!");
                        alert.showAndWait();
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Passwords do not match!");
                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Please fill in all fields!");
                alert.showAndWait();
            }
        });
    
        gridPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, reEnterPasswordLabel, reEnterPasswordField, createAccountButton, backButton);
        stackPane.getChildren().add(gridPane);
    
        return stackPane;
    }
    

    private void navigateToLoginPage() {
        LoginPane loginPane = new LoginPane(primaryStage);
        loginPane.show();
    }
}
