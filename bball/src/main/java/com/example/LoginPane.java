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

        Button loginButton = new Button("Login");
        loginButton.setFont(Font.font("Helvetica"));
        GridPane.setConstraints(loginButton, 0, 2);
        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();

            Account account = new Account(username, password);

            if (FileManager.validateUser(account)) {
                System.out.println("Login successful!");
                if (FileManager.isEmployee(account)) {
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
        createAccountButton.setFont(Font.font("Helvetica"));
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
