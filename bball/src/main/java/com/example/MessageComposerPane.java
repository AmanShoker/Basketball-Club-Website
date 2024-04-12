package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class MessageComposerPane {
    private Stage primaryStage;
    private EmployeePane employeePane;
    private VBox vbox;

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
        sendToAllCheckBox.setOnAction(e -> {
            if (sendToAllCheckBox.isSelected()) {
                addAllRecipients();
            } else {
                removeAllRecipients();
            }
        });

        TextField recipientTextField = new TextField();
        recipientTextField.setFont(Font.font("Helvetica"));

        Button addButton = new Button("Add Recipient");
        addButton.setFont(Font.font("Helvetica"));
        addButton.setOnAction(e -> {
            String username = recipientTextField.getText();
            if (usernameExists(username)) {
                // Username exists, add it to the recipient list
                addRecipient(username);
            } else {
                // Username doesn't exist, display an error message or do nothing
                System.out.println("Username does not exist!");
            }
        });

        Button sendButton = new Button("Send");
        sendButton.setFont(Font.font("Helvetica"));
        sendButton.setOnAction(e -> {
            String message = messageTextArea.getText();
            boolean sendToAll = sendToAllCheckBox.isSelected();

            // Send message logic goes here
            System.out.println("Message: " + message);
            System.out.println("Send to all: " + sendToAll);

            navigateToEmployeePage();
        });

        VBox recipientsBox = new VBox();
        recipientsBox.setId("recipientsBox");
        recipientsBox.setAlignment(Pos.CENTER_LEFT);
        recipientsBox.setSpacing(5);
        recipientsBox.setPadding(new Insets(5));

        vbox = new VBox(20);
        vbox.setAlignment(Pos.CENTER);
        vbox.setPadding(new Insets(20));
        vbox.getChildren().addAll(
                title,
                messageLabel,
                messageTextArea,
                new HBox(10, sendToAllCheckBox),
                new HBox(recipientTextField, addButton),
                recipientsBox,
                sendButton
        );

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to check if the username exists in the database
    private boolean usernameExists(String username) {
        for (String account : AccountDatabase.allAccounts.keySet()) {
            if (account.equals(username)) {
                return true;
            }
        }
        return false;
    }

    // Method to add a recipient to the recipients list
    private void addRecipient(String username) {
        VBox recipientsBox = (VBox) vbox.getChildren().get(5); // Assuming recipientsBox is the 5th child of vbox
        Label recipientLabel = new Label(username);
        recipientLabel.setFont(Font.font("Helvetica"));
        recipientsBox.getChildren().add(recipientLabel);
    }

    // Method to add all user accounts to the recipients list
    private void addAllRecipients() {
        VBox recipientsBox = (VBox) vbox.getChildren().get(5); // Assuming recipientsBox is the 5th child of vbox
        recipientsBox.getChildren().clear(); // Clear existing recipients

        for (String username : AccountDatabase.allAccounts.keySet()) {
            addRecipient(username);
        }
    }

    // Method to remove all recipients from the recipients list
    private void removeAllRecipients() {
        VBox recipientsBox = (VBox) vbox.getChildren().get(5); // Assuming recipientsBox is the 5th child of vbox
        recipientsBox.getChildren().clear(); // Clear existing recipients
    }

    private void navigateToEmployeePage() {
        EmployeePane employeePane = new EmployeePane(primaryStage);
        employeePane.show();
    }
}
