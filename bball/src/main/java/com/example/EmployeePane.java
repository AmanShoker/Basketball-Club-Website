package com.example;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EmployeePane {

    private Stage primaryStage;

    public EmployeePane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Employee Page");

        StackPane stackPane = new StackPane();
        stackPane.setAlignment(Pos.CENTER);

        Text title = new Text("Welcome to the Employee Page!");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));

        stackPane.getChildren().add(title);

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
