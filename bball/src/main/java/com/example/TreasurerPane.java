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
import javafx.scene.layout.VBox;

public class TreasurerPane {
    
    private Stage primaryStage;

    public TreasurerPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Treasurer Page");
        
        StackPane stackPane = new StackPane();

        Text title = new Text("Welcome to the Treasurer Page!");
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);

        Button generateIncomeStatement = createButtonWithImage("Generate Income Statement", "/generateStatement.png");
        generateIncomeStatement.setOnAction(e -> {
            StackPane root = new StackPane();
            FileManager.database.sortAccountByRevenueDescending();

            TreeView<String> treeView = new TreeView<>();

            // Set a dummy root initially
            TreeItem<String> dummyRoot = new TreeItem<>("Income Statement");
            treeView.setRoot(dummyRoot);

            double total = 0;
            for (int i = 0; i < AccountDatabase.userAccounts.size(); i++)
            {
                total += AccountDatabase.userAccounts.get(i).getRevenue();
            }
            total += AccountDatabase.allAccounts.get(LoginPane.getUsername()).getRevenue();

            TreeItem<String> treeRoots = new TreeItem<>("Total Income");
            TreeItem<String> treeChild11 = new TreeItem<>("Total Income: $" + String.valueOf(total));
            TreeItem<String> treeChild12 = new TreeItem<>("Check below for income sources");
            treeRoots.getChildren().addAll(treeChild11, treeChild12);
            dummyRoot.getChildren().add(treeRoots);

            TreeItem<String> treasureRoot = new TreeItem<>("Sponsorships and Donations");
            TreeItem<String> treasureChild = new TreeItem<>("Revenue: $" + String.valueOf(AccountDatabase.allAccounts.get(LoginPane.getUsername()).getRevenue()));
            treasureRoot.getChildren().addAll(treasureChild);
            dummyRoot.getChildren().add(treasureRoot);

            // Populate the tree with root items and their children
            for (int i = 0; i < AccountDatabase.userAccounts.size(); i++) 
            {
                if (AccountDatabase.userAccounts.get(i).getRevenue() <= 0)
                {
                    continue;
                }
                TreeItem<String> treeRoot = new TreeItem<>(AccountDatabase.userAccounts.get(i).getUsername());
                TreeItem<String> treeChild1 = new TreeItem<>("Revenue: $" + String.valueOf(AccountDatabase.userAccounts.get(i).getRevenue()));
                // Add child items for each root item
                treeRoot.getChildren().add(treeChild1);
            //    treeRoot.getChildren().add(treeChild2);
              //  treeRoot.getChildren().add(treeChild3);
                dummyRoot.getChildren().add(treeRoot);
            }

            root.getChildren().add(treeView);

            VBox vbox = new VBox(treeView);
            Scene scene = new Scene(vbox, 275, 400);
            Stage dialog = new Stage();
            dialog.setScene(scene);
            dialog.setTitle("Income Statement");
            dialog.initOwner(primaryStage);
            dialog.showAndWait();
        });

        HBox buttonBox = new HBox(20);
        buttonBox.setAlignment(Pos.CENTER);
        buttonBox.getChildren().addAll(
            generateIncomeStatement, 
            createButtonWithImage("Update Expenses", "/updateExpenses.png"),
            createButtonWithImage("View Monthly Profit", "/viewProfit.png")
        );

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
