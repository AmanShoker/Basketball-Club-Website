package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SchedulePane {

    private Stage primaryStage;

    public SchedulePane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {

        primaryStage.setTitle("Schedule Your Classes");

        StackPane stackPane = new StackPane();

        Label titleLabel = new Label("Select a Class:");
        titleLabel.setFont(Font.font("Helvetica"));
        titleLabel.setPadding(new Insets(30,0,0,0));

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3", "Option 4", "Option 5");
        comboBox.setPrefWidth(200);
        comboBox.setVisibleRowCount(5);

        Button yesButton = new Button("Continue");
        yesButton.setFont(Font.font("Helvetica"));
        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Helvetica"));

        backButton.setPrefWidth(100);
        backButton.setPrefHeight(50);

        yesButton.setDisable(true); 
        yesButton.setPrefWidth(100);
        yesButton.setPrefHeight(50);

        HBox hBox = new HBox(50, backButton, yesButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,50,0));

        VBox vBox = new VBox(10, titleLabel,comboBox);   
        vBox.setPadding(new Insets(50, 0, 0, 0));
        vBox.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setBottom(hBox);

        Text title = new Text("Schedule Your Classes");
        stackPane.setAlignment(Pos.TOP_CENTER);
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);

        stackPane.getChildren().addAll(title, borderPane);
        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}