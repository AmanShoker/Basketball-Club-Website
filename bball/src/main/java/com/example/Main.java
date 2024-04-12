// Main.java
package com.example;

import java.io.File;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {
    @Override
    public void start(Stage primaryStage) {
        LoginPane loginPane = new LoginPane(primaryStage);
        loginPane.show();
    }

    public static void main(String[] args) {
        FileManager.createDatabase();
        launch(args);
    }
}
