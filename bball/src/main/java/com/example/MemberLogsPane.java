package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MemberLogsPane 
{
    private Stage primaryStage;

    public MemberLogsPane(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
    }

    public void show()
    {
        primaryStage.setTitle("Member Logs Page");

        StackPane stackPane = new StackPane();

        Text title = new Text("Welcome to the Member Logs Page!");
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPadding(new Insets(20,0,0,0));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);

        HBox buttonBox = new HBox();
        buttonBox.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 10px; -fx-font-family: Helvetica;");
        backButton.setPrefSize(50, 35);
        buttonBox.setPadding(new Insets(0,550,0,0));
        backButton.setOnAction(e -> 
        {
            EmployeePane employeePane = new EmployeePane(primaryStage);
            employeePane.show();
        });
        buttonBox.getChildren().add(backButton);
        stackPane.setAlignment(buttonBox, Pos.TOP_LEFT);

        stackPane.getChildren().addAll(title, buttonBox);

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButtonWithImage(String buttonText, String imagePath) 
    {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        Button button = new Button(buttonText, imageView);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        button.setStyle("-fx-font-size: 1px; -fx-font-family: Helvetica;");
        return button;
    }
}