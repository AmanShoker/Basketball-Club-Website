package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
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
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPadding(new Insets(20));

        // Text title at the top
        Text title = new Text("Welcome to the Employee Page!");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));

        // HBox to arrange buttons horizontally
        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        Button memberLogsButton = createButtonWithImage("Member Logs", "/data.png");
        memberLogsButton.setOnAction(e -> {
            MemberLogsPane memberLogsPane = new MemberLogsPane(primaryStage);
            memberLogsPane.show();
        });

        Button sendMessageButton = createButtonWithImage("Send a Message", "/schedule.png");
        sendMessageButton.setOnAction(e -> {
            MessageComposerPane messageComposerPane = new MessageComposerPane(primaryStage);
            messageComposerPane.show();
        });

        buttonBox.getChildren().addAll(
                memberLogsButton,
                createButtonWithImage("Finances", "/money.png"),
                createButtonWithImage("Scheduling", "/schedule.png"),
                sendMessageButton);
        
        // Position buttons slightly lower
        StackPane.setMargin(buttonBox, new Insets(50, 0, 0, 0));

        // Add title and buttons to stack pane
        stackPane.getChildren().addAll(title, buttonBox);

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Create a button with text and corresponding image
    private Button createButton(String buttonText, Image image) {
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(50);
        imageView.setFitWidth(50);
        Button button = new Button(buttonText, imageView);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        button.setStyle("-fx-font-size: 16px; -fx-font-family: Helvetica;");
        return button;
    }

    // Create a button with text and corresponding image
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
