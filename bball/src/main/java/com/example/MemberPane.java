package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.VBox;

public class MemberPane {
    
    private Stage primaryStage;

    public MemberPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Member Page");
        
        StackPane stackPane = new StackPane();

        Text title = new Text("Welcome to the Member Page!");
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
        paymentButton.setOnAction(event -> {
            PaymentPane paymentPane = new PaymentPane(primaryStage);
            paymentPane.show();
        });

        buttonBox.getChildren().addAll(scheduleButton, paymentButton);

        // Create logout button
        Button logoutButton = new Button("Logout");
        logoutButton.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));
        logoutButton.setOnAction(e -> {
            LoginPane loginPane = new LoginPane(primaryStage);
            loginPane.show();
        });

        VBox layout = new VBox(20);
        layout.getChildren().addAll(title, buttonBox, logoutButton);
        layout.setAlignment(Pos.CENTER);
        stackPane.getChildren().add(layout);
        
        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        Account user = AccountDatabase.allAccounts.get(LoginPane.getUsername());

        if (user.getMessages().size() != 0){
            for (String messages : user.getMessages()){
                Alert messageAlert = new Alert(AlertType.INFORMATION);
                messageAlert.setTitle("New Message");
                messageAlert.setHeaderText(messages);
                messageAlert.showAndWait();
                user.removeMessages();
            }
        } 
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