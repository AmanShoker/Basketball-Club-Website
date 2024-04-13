package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SchedulePane {

    private Stage primaryStage;

    public SchedulePane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {

        primaryStage.setTitle("Schedule Your Classes");

        StackPane stackPane = new StackPane();

        Label titleLabel = new Label("Select a Class:");
        titleLabel.setFont(Font.font("Helvetica"));

        ComboBox<String> comboBox = new ComboBox<>();
        comboBox.getItems().addAll("Week 1", "Week 2", "Week 3", "Week 4");

        comboBox.setPrefWidth(200);
        comboBox.setVisibleRowCount(4);

        Button yesButton = new Button("Continue");
        yesButton.setFont(Font.font("Helvetica"));

        Button backButton = new Button("Back");
        backButton.setFont(Font.font("Helvetica"));

        backButton.setPrefWidth(100);
        backButton.setPrefHeight(50);

        yesButton.setDisable(true); 
        yesButton.setPrefWidth(100);
        yesButton.setPrefHeight(50);

        Account user = AccountDatabase.allAccounts.get(LoginPane.getUsername());

        comboBox.setOnAction(e -> {
            if (comboBox.getValue() != null && user.getClasses().contains(comboBox.getValue()) == false) {
                yesButton.setDisable(false);
            } else {
                yesButton.setDisable(true);
            }
        });

        backButton.setOnAction(e -> {
            MemberPane memberPane = new MemberPane(primaryStage);
            memberPane.show();
        });

        yesButton.setOnAction(e -> {
            
            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are You Sure You Want To Proceed?");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {

                    Alert paymentAlert = new Alert(AlertType.CONFIRMATION);
                    FileManager.getDatabase().sortAccountByAttendenceDescending();
                    if (AccountDatabase.userAccounts.size() >= 10){ 
                        if (AccountDatabase.userAccounts.subList(0, 9).contains(user) == true || user.getAttendence() >= 12) {
                            user.AddToBalance(9);
                        } else {
                            user.AddToBalance(10);
                        }
                    } else {
                        user.AddToBalance(9);
                    }
                    
                    user.attendedClass();
                    user.addClasses(comboBox.getValue());
                    yesButton.setDisable(true);
                    paymentAlert.setTitle("Transaction Complete");
                    paymentAlert.setHeaderText("A Charge Has Been Added to Your Account, Would You Like to Pay This Balance Now?");
                    paymentAlert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

                    paymentAlert.showAndWait().ifPresent(event -> {
                        if (event == buttonTypeYes) {
                            PaymentPane paymentPane = new PaymentPane(primaryStage);
                            paymentPane.show();
                        } 
                    }
                    );
                }
            });
        });

        HBox hBox = new HBox(50, backButton, yesButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setPadding(new Insets(0,0,50,0));

        VBox vBox = new VBox(10, titleLabel,comboBox);   
        vBox.setPadding(new Insets(100, 0, 0, 0));
        vBox.setAlignment(Pos.TOP_CENTER);

        BorderPane borderPane = new BorderPane();
        borderPane.setCenter(vBox);
        borderPane.setBottom(hBox);

        Text title = new Text("Schedule Your Classes");
        stackPane.setAlignment(Pos.TOP_CENTER);
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));
        title.setLayoutY(50);

        stackPane.getChildren().addAll(title, borderPane);
        stackPane.setPadding(new Insets(10, 0,0,0));
        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    
}