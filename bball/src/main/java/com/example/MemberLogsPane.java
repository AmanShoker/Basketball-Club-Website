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
        stackPane.setPadding(new Insets(20));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);
        stackPane.getChildren().add(title);

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
