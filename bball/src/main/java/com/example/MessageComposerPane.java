package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MessageComposerPane {
    private Stage primaryStage;

    public MessageComposerPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Message Composer Page");

        StackPane stackPane = new StackPane();

        Label title = new Label("Compose Message");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 24));

        Label messageLabel = new Label("Message:");
        messageLabel.setFont(Font.font("Helvetica"));
        messageLabel.setWrapText(true);

        TextArea messageTextArea = new TextArea();
        messageTextArea.setPrefRowCount(5);
        messageTextArea.setWrapText(true);
        messageTextArea.setFont(Font.font("Helvetica"));
        messageTextArea.setMinWidth(300); // Set a minimum width to prevent truncation
        messageTextArea.setMaxWidth(Double.MAX_VALUE);

        CheckBox sendToAllCheckBox = new CheckBox("Send to all members");
        sendToAllCheckBox.setFont(Font.font("Helvetica"));

        ToggleGroup messageTypeGroup = new ToggleGroup();
        RadioButton practiceDetailsRadioButton = new RadioButton("Practice Details");
        practiceDetailsRadioButton.setToggleGroup(messageTypeGroup);
        practiceDetailsRadioButton.setSelected(true);
        practiceDetailsRadioButton.setFont(Font.font("Helvetica"));

        RadioButton paymentReminderRadioButton = new RadioButton("Payment Reminder");
        paymentReminderRadioButton.setToggleGroup(messageTypeGroup);
        paymentReminderRadioButton.setFont(Font.font("Helvetica"));

        Button sendButton = new Button("Send");
        sendButton.setFont(Font.font("Helvetica"));
        sendButton.setOnAction(e -> {
            String message = messageTextArea.getText();
            boolean sendToAll = sendToAllCheckBox.isSelected();
            String messageType = practiceDetailsRadioButton.isSelected() ? "Practice Details" : "Payment Reminder";

            // Send message logic goes here
            System.out.println("Message: " + message);
            System.out.println("Send to all: " + sendToAll);
            System.out.println("Message Type: " + messageType);

            // Close the window after sending the message
            primaryStage.close();
        });

        VBox vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
            title,
            messageLabel,
            messageTextArea,
            new HBox(10, sendToAllCheckBox, practiceDetailsRadioButton, paymentReminderRadioButton),
            sendButton
        );

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

/* public class MessageComposerPane 
{
    private Stage primaryStage;

    public MessageComposerPane(Stage primaryStage) 
    {
        this.primaryStage = primaryStage;
    }

    public void show()
    {
        primaryStage.setTitle("Message Composer Page");

        StackPane stackPane = new StackPane();

        Text title = new Text("Welcome to the Message Composer Page!");
        stackPane.setAlignment(Pos.TOP_CENTER);
        stackPane.setPadding(new Insets(20));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);
        stackPane.getChildren().add(title);

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
} */
