package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.scene.Node;


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

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 10px; -fx-font-family: Helvetica;");
        backButton.setPrefSize(50, 35);
        backButton.setOnAction(e -> 
        {
            EmployeePane employeePane = new EmployeePane(primaryStage);
            employeePane.show();
        });


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

            if (sendToAll) {
                // Send message to all members
                sendToAllMembers(message);
            } else {
                // Send message to specific recipients
                sendToSelectedRecipients(message);
            }
            
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
                sendButton,
                backButton
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
        // Create a new HBox to hold the recipient list
        HBox hBox = new HBox();
        hBox.setSpacing(10); // Add spacing between the VBoxes in the HBox
        hBox.setAlignment(Pos.CENTER); // Center the HBox within its parent container
        
        // Clear the existing children in the recipientsBox
        VBox recipientsBox = (VBox) vbox.getChildren().get(5); // Assuming recipientsBox is the 5th child of vbox
        recipientsBox.getChildren().clear();
    
        // Create a new VBox to hold up to five recipient labels
        VBox currentVBox = new VBox();
        currentVBox.setAlignment(Pos.CENTER); // Center the recipient labels in the VBox
        int count = 0;
    
        // Iterate through all usernames in AccountDatabase.allAccounts.keySet()
        for (String username : AccountDatabase.allAccounts.keySet()) {
            // Create a new label for the username
            Label recipientLabel = new Label(username);
            recipientLabel.setFont(Font.font("Helvetica"));
    
            // Add the label to the current VBox
            currentVBox.getChildren().add(recipientLabel);
            
            // Increment the count
            count++;
    
            // If we have added 5 labels to the current VBox, add it to the HBox and reset the count
            if (count == 5) {
                hBox.getChildren().add(currentVBox);
                currentVBox = new VBox();
                currentVBox.setAlignment(Pos.CENTER);
                count = 0;
            }
        }
    
        // If there are any remaining labels in the current VBox, add it to the HBox
        if (!currentVBox.getChildren().isEmpty()) {
            hBox.getChildren().add(currentVBox);
        }
    
        // Add the HBox to the recipientsBox
        recipientsBox.getChildren().add(hBox);
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

    // Method to send message to all members
    private void sendToAllMembers(String message) {
        for (String username : AccountDatabase.allAccounts.keySet()) {
            Account account = AccountDatabase.allAccounts.get(username);
            account.addMessage(LoginPane.getUsername() + ": " + message);
        }
    }

    // Method to send message to selected recipients
    private void sendToSelectedRecipients(String message) {
        VBox recipientsBox = (VBox) vbox.getChildren().get(5);
        for (Node node : recipientsBox.getChildren()) {
            if (node instanceof Label) {
                Label recipientLabel = (Label) node;
                String recipientUsername = recipientLabel.getText();
                if (usernameExists(recipientUsername)) {
                    Account account = AccountDatabase.allAccounts.get(recipientUsername);
                    account.addMessage(LoginPane.getUsername() + ": " + message);
                } else {
                    System.out.println("Recipient username does not exist: " + recipientUsername);
                }
            }
        }
    }
}
