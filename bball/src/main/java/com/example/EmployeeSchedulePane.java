package com.example;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.util.ArrayList;
import javafx.scene.control.ScrollPane;

public class EmployeeSchedulePane {
    private Stage primaryStage;

    public EmployeeSchedulePane(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    public void show() {
        primaryStage.setTitle("Your Classes");

        VBox container = new VBox(10); // Initialize VBox directly in the method
        container.setAlignment(Pos.CENTER);
        container.setPadding(new Insets(20));

        StackPane stackPane = new StackPane();

        // Retrieve the list of classes assigned to the current coach
        ArrayList<String> coachClasses = AccountDatabase.allAccounts.get(LoginPane.getUsername()).getClasses();

        // Iterate over each class
        for (String className : coachClasses) {
            // Create a label for the class name
            Label classLabel = new Label(className);
            classLabel.setFont(Font.font("Helvetica", FontWeight.BOLD, 16));

            // Retrieve the list of students enrolled in this class
            ArrayList<String> studentsInClass = getStudentsInClass(className);

            // Create a VBox to hold the list of students
            VBox studentList = new VBox(5);

            // Add each student to the studentList VBox
            for (String student : studentsInClass) {
                Label studentLabel = new Label(student);
                studentList.getChildren().add(studentLabel);
            }

            // Add the class name label and student list to the main container
            container.getChildren().addAll(classLabel, studentList);
        }

        // Create a ScrollPane and set its content to the VBox container
        ScrollPane scrollPane = new ScrollPane(container);
        scrollPane.setFitToWidth(true); // Allow horizontal scrolling if necessary

        // Add the ScrollPane to the StackPane
        stackPane.getChildren().add(scrollPane);

        // Create a back button
        Button backButton = new Button("Back");
        backButton.setOnAction(e -> {
            EmployeePane employeePane = new EmployeePane(primaryStage);
            employeePane.show();
        });

        // Add the back button to the VBox container
        container.getChildren().add(backButton);

        // Create the scene and set it to the primary stage
        Scene scene = new Scene(stackPane, 400, 300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    // Method to retrieve the list of students enrolled in a specific class
    private ArrayList<String> getStudentsInClass(String className) {
        // Retrieve the list of students enrolled in the specified class from the AccountDatabase
        ArrayList<String> students = new ArrayList<>();
        for (Account userAccount : AccountDatabase.userAccounts) {
            if (userAccount.getClasses().contains(className)) {
                students.add(userAccount.getUsername());
            }
        }
        return students;
    }
}