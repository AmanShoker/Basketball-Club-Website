package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MemberPane {
    
    private Stage primaryStage;

    public MemberPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Member Page");
        
        StackPane stackPane = new StackPane();

        Text title = new Text("Welcome to the Member Page!");
        stackPane.setAlignment(Pos.TOP_CENTER);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 30));
        title.setLayoutY(50);

        stackPane.getChildren().add(title);


        Image image = new Image(getClass().getResourceAsStream("/Drose.png"));

        ImageView imageView = new ImageView(image);
        stackPane.getChildren().add(imageView);
        stackPane.setAlignment(imageView, Pos.CENTER_LEFT);

        Button payClassButton = new Button("Pay for Class");
        payClassButton.setMinWidth(100);
        payClassButton.setMinHeight(70);

        StackPane buttonPane = new StackPane();
        buttonPane.setAlignment(Pos.CENTER_RIGHT);

        buttonPane.getChildren().add(payClassButton);
        StackPane.setMargin(payClassButton, new Insets(0, 100,0, 0));

        Scene scene = new Scene(new StackPane(stackPane, buttonPane), 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();


    }
}
