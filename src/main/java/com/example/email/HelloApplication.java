package com.example.email;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("panelLogin.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 400, 400);
        stage.setTitle("E-mail");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        readFromFile();
        launch();
    }
    public static void readFromFile() throws IOException, ClassNotFoundException {
        DataBase.readAccountsFromFile();
        DataBase.readEmailsFromFile();
    }
    public static void saveToFile() throws IOException, ClassNotFoundException {
        DataBase.saveAccountsToFile();
        DataBase.saveEmailsToFile();
    }
}