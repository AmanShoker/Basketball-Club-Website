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
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        StackPane.setAlignment(title, Pos.TOP_CENTER);
        StackPane.setMargin(title, new Insets(20, 0, 0, 0));
        stackPane.getChildren().add(title);

        GridPane gridPane = new GridPane();
        gridPane.setVgap(20);
        gridPane.setHgap(10);
        gridPane.setAlignment(Pos.CENTER);

        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);
        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        GridPane.setConstraints(usernameField, 1, 0);

        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");
        GridPane.setConstraints(passwordField, 1, 1);

        Button createAccountButton = new Button("Create Account");
        GridPane.setConstraints(createAccountButton, 0, 2);
        /*createAccountButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!username.isEmpty() && !password.isEmpty()) {
                FileManager.registerUser(username, password, false); 
                System.out.println("Account created successfully!");
                navigateToLoginPage();
            } else {
                System.out.println("Username and password cannot be empty.");
            }
        });*/

        createAccountButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (!username.isEmpty() && !password.isEmpty()) {
                boolean success = FileManager.registerUser(username, password, false);
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
                    navigateToLoginPage();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Username and password cannot be empty!");
                alert.showAndWait();
            }
        });

        gridPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, createAccountButton);
        stackPane.getChildren().add(gridPane);

        return stackPane;
    }

    private void navigateToLoginPage() {
        LoginPane loginPane = new LoginPane(primaryStage);
        loginPane.show();
    }
}