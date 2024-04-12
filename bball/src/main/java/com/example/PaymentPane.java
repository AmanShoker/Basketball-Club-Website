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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class PaymentPane {

    private Stage primaryStage;

    public PaymentPane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {

        primaryStage.setTitle("Payment Page");

        StackPane stackPane = new StackPane();

        Text title = new Text("Payment Hub");
        title.setFont(Font.font("Helvetica"));
        title.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));

        Text balance = new Text("Charges Due");
        balance.setFont(Font.font("Helvetica"));
        balance.setFont(Font.font("Helvetica", FontWeight.BOLD, 30));

        Account user = AccountDatabase.allAccounts.get(LoginPane.getUsername());
        double amount_due = user.getBalance();

        Text amount = new Text(Double.toString(amount_due));
        amount.setFont(Font.font("Helvetica"));
        amount.setFont(Font.font("Helvetica", FontWeight.BOLD, 40));

        amount.setFill(Color.GREEN);

        VBox vBox = new VBox(20, balance, amount);
        vBox.setAlignment(Pos.TOP_CENTER);

        stackPane.getChildren().addAll(title, vBox);
        stackPane.setAlignment(Pos.TOP_CENTER);

        stackPane.setMargin(vBox, new Insets(100,0,0,0));
        stackPane.setMargin(title, new Insets(10,0,0,0));

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}