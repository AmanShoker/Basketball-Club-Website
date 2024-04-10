package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPane {
    private Stage primaryStage;

    public LoginPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Login");
        Pane loginPane = createLoginPane();
        Scene scene = new Scene(loginPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Pane createLoginPane() {
        StackPane stackPane = new StackPane();
        Text title = new Text("Welcome to TMU Basketball Club!");
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

        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 0, 2);
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (FileManager.validateUser(username, password)) {
                System.out.println("Login successful!");
                if (FileManager.isEmployee(username, password)) {
                    navigateToEmployeePage();
                } else {
                    navigateToMemberPage();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Incorrect Username or Password!");
                alert.showAndWait();
            }
        });

        Button createAccountButton = new Button("Create Account");
        GridPane.setConstraints(createAccountButton, 1, 2);
        createAccountButton.setOnAction(e -> {
            RegistrationPane registrationPane = new RegistrationPane(primaryStage);
            registrationPane.show();
        });

        gridPane.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, createAccountButton);
        stackPane.getChildren().add(gridPane);

        return stackPane;
    }
    private void navigateToMemberPage() {
        MemberPane memberPane = new MemberPane(primaryStage);
        memberPane.show();
    }
    
    private void navigateToEmployeePage() {
        EmployeePane employeePane = new EmployeePane(primaryStage);
        employeePane.show();
    }
    
}
