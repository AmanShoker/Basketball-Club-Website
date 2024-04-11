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
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);


        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button scheduleButton = createButtonWithImage("Schedule Class", "/jumpball.png");
        scheduleButton.setOnAction(e -> {
            SchedulePane schedulePane = new SchedulePane(primaryStage);
            schedulePane.show();
        });
        Button paymentButton = createButtonWithImage("Make Payment", "/payment.png");
        buttonBox.getChildren().addAll(scheduleButton, paymentButton);

        StackPane.setMargin(buttonBox, new Insets(0, 0, 0, 0));

        stackPane.getChildren().addAll(buttonBox, title);

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButtonWithImage(String buttonText, String imagePath) {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        Button button = new Button(buttonText, imageView);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        button.setStyle("-fx-font-size: 16px; -fx-font-family: Helvetica;");
        return button;
    }
}
