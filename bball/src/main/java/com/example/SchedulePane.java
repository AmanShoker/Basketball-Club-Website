package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
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

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Option 1", "Option 2", "Option 3", "Option 4", "Option 5");
        comboBox.setPrefWidth(150);
        comboBox.setVisibleRowCount(5);

        Button yesButton = new Button("Yes");
        yesButton.setDisable(true); 

        comboBox.setOnAction(event -> {
            if (comboBox.getValue() != null) {
                yesButton.setDisable(false); 
            }
        });

        VBox vBox = new VBox(10, comboBox, yesButton);
        vBox.setAlignment(Pos.TOP_CENTER);
        Insets margin = new Insets(50, 0, 0, 0);     
        vBox.setPadding(margin);
        stackPane.getChildren().add(vBox);

        Text title = new Text("Schedule Your Classes");
        stackPane.setAlignment(Pos.TOP_CENTER);
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);
        stackPane.getChildren().add(title);
        
        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


    }

}