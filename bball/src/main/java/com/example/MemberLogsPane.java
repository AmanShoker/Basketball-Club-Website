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

   //     HBox buttonBox = new HBox();
     //   buttonBox.setAlignment(Pos.TOP_LEFT);
        Button backButton = new Button("Back");
        backButton.setStyle("-fx-font-size: 10px; -fx-font-family: Helvetica;");
        backButton.setPrefSize(50, 35);
      //  backButton.setPadding(new Insets(0,0,0,0));
       // buttonBox.setPadding(new Insets(0,0,0,0));
        backButton.setOnAction(e -> 
        {
            EmployeePane employeePane = new EmployeePane(primaryStage);
            employeePane.show();
        });
      //  buttonBox.getChildren().add(backButton);
        //stackPane.setAlignment(buttonBox, Pos.TOP_LEFT);

        Label message = new Label("Use the following filters to sort the list of club members: ");
        message.setStyle("-fx-font-size: 17px; -fx-font-family: Helvetica;");
       /*  VBox messageLayout = new VBox();
        messageLayout.getChildren().addAll(message);
        messageLayout.setPadding(new Insets(52, 0, 0, 15));
        messageLayout.setAlignment(Pos.TOP_LEFT); */

        ChoiceBox<String> filtersMenu = new ChoiceBox<>();
        filtersMenu.getItems().addAll("Alphabetical", "Attendance", "Paid");
        filtersMenu.setValue("Alphabetical");

       /*  VBox layout = new VBox();
        layout.getChildren().addAll(filtersMenu);
        layout.setPadding(new Insets(50, 50, 0, 0));
        layout.setAlignment(Pos.TOP_RIGHT);

        BorderPane pane = new BorderPane();
        pane.setTop(layout);
        pane.setLeft(buttonBox);
        pane.setAlignment(layout, Pos.TOP_RIGHT);
        pane.setAlignment(buttonBox, Pos.TOP_LEFT);
        //pane.getChildren().addAll(layout, messageLayout); */


        stackPane.getChildren().addAll(title, message, backButton, filtersMenu);
        stackPane.setAlignment(backButton, Pos.TOP_LEFT);
        stackPane.setAlignment(filtersMenu, Pos.TOP_RIGHT);
        stackPane.setMargin(filtersMenu, new Insets(50, 50, 0, 0));
        stackPane.setMargin(message, new Insets(52, 0, 0, 15));
        stackPane.setAlignment(message, Pos.TOP_LEFT);


        Scene scene = new Scene(stackPane, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button createButtonWithImage(String buttonText, String imagePath) 
    {
        Image image = new Image(getClass().getResourceAsStream(imagePath));
        ImageView imageView = new ImageView(image);
        imageView.setFitHeight(10);
        imageView.setFitWidth(10);
        Button button = new Button(buttonText, imageView);
        button.setContentDisplay(javafx.scene.control.ContentDisplay.TOP);
        button.setStyle("-fx-font-size: 1px; -fx-font-family: Helvetica;");
        return button;
    }
}