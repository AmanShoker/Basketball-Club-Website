package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
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
        stackPane.setPadding(new Insets(20,0,0,0));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);

        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 10px; -fx-font-family: Helvetica;");
        backButton.setPrefSize(50, 35);
        backButton.setOnAction(e -> 
        {
            EmployeePane employeePane = new EmployeePane(primaryStage);
            employeePane.show();
        });

        Label message = new Label("Use the following filters to sort the list of club members: ");
        message.setStyle("-fx-font-size: 17px; -fx-font-family: Helvetica;");

        ChoiceBox<String> filtersMenu = new ChoiceBox<>();
        filtersMenu.getItems().addAll("Alphabetical", "Attendance Ascending", "Attendance Descending", "Balance Ascending", "Balance Descending", "Revenue Ascending", "Revenue Descending");
        filtersMenu.setValue("Alphabetical");

        Button submitButton = new Button("Submit");
        submitButton.setStyle("-fx-font-size: 20px; -fx-font-family: Helvetica;");
        submitButton.setPrefSize(150, 75);
        submitButton.setOnAction(e -> {
            StackPane root = new StackPane();
            String option = filtersMenu.getValue();
            if (option.equals("Alphabetical"))
            {
                AccountDatabase.sortUsersByName();
            }
            else if (option.equals("Attendance Ascending"))
            {
                FileManager.database.sortAccountByAttendenceAscending();
            }
            else if (option.equals("Attendance Descending"))
            {
                FileManager.database.sortAccountByAttendenceDescending();
            }
            else if (option.equals("Balance Ascending"))
            {
                FileManager.database.sortAccountByBalanceAscending();
            }
            else if (option.equals("Balance Descending"))
            {
                FileManager.database.sortAccountByBalanceDescending();
            }
            else if (option.equals("Revenue Ascending"))
            {
                FileManager.database.sortAccountByRevenueAscending();
            }
            else
            {
                FileManager.database.sortAccountByRevenueDescending();
            }

            TreeView<String> treeView = new TreeView<>();

            // Set a dummy root initially
            TreeItem<String> dummyRoot = new TreeItem<>("List Of Members");
            treeView.setRoot(dummyRoot);

            // Populate the tree with root items and their children
            for (int i = 0; i < AccountDatabase.userAccounts.size(); i++) 
            {
                TreeItem<String> treeRoot = new TreeItem<>(AccountDatabase.userAccounts.get(i).getUsername());

                TreeItem<String> treeChild1 = new TreeItem<>("Phone Number: " + AccountDatabase.userAccounts.get(i).getPhoneNumber());
                TreeItem<String> treeChild2 = new TreeItem<>("Address: " + AccountDatabase.userAccounts.get(i).getAddress());
                TreeItem<String> treeChild3;
                
                 if (AccountDatabase.userAccounts.get(i).getBalance() == 0)
                {
                    treeChild3 = new TreeItem<>("Paid");
                }
                else
                {
                    treeChild3 = new TreeItem<>("Not Paid");
                } 

                TreeItem<String> treeChild4 = new TreeItem<>("Balance: $" + String.valueOf(AccountDatabase.userAccounts.get(i).getBalance()));
                TreeItem<String> treeChild5 = new TreeItem<>("Revenue: $" + String.valueOf(AccountDatabase.userAccounts.get(i).getRevenue()));
                TreeItem<String> treeChild6 = new TreeItem<>("Classes Attended: " + String.valueOf(AccountDatabase.userAccounts.get(i).getAttendence()));
                // Add child items for each root item
                treeRoot.getChildren().addAll(treeChild1, treeChild2, treeChild3, treeChild4, treeChild5, treeChild6);
                dummyRoot.getChildren().add(treeRoot);
            }

            root.getChildren().add(treeView);

            VBox vbox = new VBox(treeView);
            Scene scene = new Scene(vbox, 250, 400);
            Stage dialog = new Stage();
            dialog.setScene(scene);
            dialog.setTitle("Log of Members");
            dialog.initOwner(primaryStage);
            dialog.showAndWait();
        });

        stackPane.getChildren().addAll(title, message, backButton, filtersMenu, submitButton);
        stackPane.setMargin(backButton, new Insets(95, 225, 0, 0));
        stackPane.setAlignment(backButton, Pos.TOP_CENTER);
        stackPane.setAlignment(filtersMenu, Pos.TOP_CENTER);
        stackPane.setMargin(filtersMenu, new Insets(100, 0, 0, 0));
        stackPane.setMargin(message, new Insets(52, 0, 0, 0));
        stackPane.setAlignment(message, Pos.TOP_CENTER);
        stackPane.setAlignment(submitButton, Pos.BOTTOM_CENTER);
        stackPane.setMargin(submitButton, new Insets(0, 0, 10, 0));


        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    public TreeItem<String> makeBranch(String title, TreeItem<String> parent)
    {
        TreeItem<String> item = new TreeItem<>(title);
        item.setExpanded(true);
        parent.getChildren().add(item);
        return item;
    }
}