package com.example.email;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class SendEmail {
    @FXML
    TextField textField_email, textField_title;
    @FXML
    TextArea textArea_text;
    Account account;
    public void initialize(){
        new Thread(() -> {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) textField_email.getScene().getWindow();
            account = (Account) stage.getUserData();
        }).start();
    }
    public void back() throws IOException {
        nextPage("panelMain.fxml");
    }
    public void send() throws IOException {
        String receiver = textField_email.getText() , title = textField_title.getText() , text = textArea_text.getText();
        if (receiver.equals(""))
            showMessage("Error","TextField receiver is empty.");
        else if(title.equals(""))
            showMessage("Error","TextField title is empty.");
        else if(text.equals(""))
            showMessage("Error","TextField text is empty.");
        if (receiver.length()<11 || !receiver.endsWith("@Custom.ir"))
            showMessage("Error","The receiver email is invalid.");
        else{
            Account accountReceiver = DataBase.findEmail(receiver);
            if (accountReceiver==null){
                showMessage("Error","Email not exist.");
            }else{
                Email email = new Email(account.getEmail() , accountReceiver.getEmail()  , title , text, new Date());
                account.emails.add(email.getID());
                accountReceiver.emails.add(email.getID());
                DataBase.emails.add(email);
                DataBase.saveEmailsToFile();
                DataBase.saveEmailsToFile();
                showMessage("Info","Email sent successfully.");
                nextPage("panelMain.fxml");
            }
        }
    }
    public static void showMessage(String type , String text){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        if (type.equals("Info"))
            alert.setAlertType(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.getDialogPane().setPrefSize(300,100);
        alert.showAndWait();
    }
    public void nextPage(String nameFile) throws IOException {
        Stage stage = (Stage) textField_email.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource(nameFile));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
}