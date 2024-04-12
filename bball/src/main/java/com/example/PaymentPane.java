package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
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

        Button wholePayment = new Button("Pay Entire Balance");
        Button onePayment = new Button("Pay For One Class");
        Button back = new Button("Back");

        wholePayment.setPrefSize(100,50);
        onePayment.setPrefSize(100,50);
        back.setPrefSize(100,30);

        HBox hbox = new HBox(20, wholePayment, onePayment);
        hbox.setAlignment(Pos.CENTER);

        VBox vBox = new VBox(20, balance, amount, hbox, back);
        vBox.setAlignment(Pos.TOP_CENTER);

        wholePayment.setOnAction( e -> {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are You Sure You Want To Proceed?");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            
            alert.showAndWait().ifPresent(response -> {
                if (response == buttonTypeYes) {

                    Alert paymentAlert = new Alert(AlertType.INFORMATION);
                    paymentAlert.setTitle("Transaction Complete");
                    paymentAlert.setHeaderText("Your Payment Has Been Processed!");


                    user.payBalance(user.getBalance());
                    vBox.getChildren().remove(amount);

                    amount.setText(Double.toString(0));

                    vBox.getChildren().add(1, amount);
                    paymentAlert.setTitle("Transaction Complete");
                    paymentAlert.show();

                }
            });
        });

        onePayment.setOnAction( e -> {

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("Confirmation Dialog");
            alert.setHeaderText("Are You Sure You Want To Proceed?");
            ButtonType buttonTypeYes = new ButtonType("Yes");
            ButtonType buttonTypeNo = new ButtonType("No");
            alert.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);
            
            alert.showAndWait().ifPresent(response -> {

                if (response == buttonTypeYes) {

                    Alert paymentAlert = new Alert(AlertType.INFORMATION);
                    paymentAlert.setTitle("Transaction Complete");
                    paymentAlert.setHeaderText("Your Payment Has Been Processed!");

                    user.payBalance(10);
                    vBox.getChildren().remove(amount);

                    amount.setText(Double.toString(user.getBalance()));

                    vBox.getChildren().add(1, amount);
                    paymentAlert.setTitle("Transaction Complete");
                    paymentAlert.show();

                }
            });
        });

        back.setOnAction(click -> {
            MemberPane memberPane = new MemberPane(primaryStage);
            memberPane.show();
        }
        );


        stackPane.getChildren().addAll(title, vBox);
        stackPane.setAlignment(Pos.TOP_CENTER);

        stackPane.setMargin(vBox, new Insets(100,0,0,0));
        stackPane.setMargin(title, new Insets(10,0,0,0));

        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}

