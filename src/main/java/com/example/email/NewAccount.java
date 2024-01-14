package com.example.email;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.SceneAntialiasing;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Date;

public class NewAccount {
    @FXML
    TextField textField_firstName , textField_lastName , textField_Email , textField_password;
    public void back() throws IOException {
        nextPage("panelLogin.fxml",null);
    }
    public void save() throws IOException {
        String email = textField_Email.getText() , password = textField_password.getText()
                , firstName = textField_firstName.getText() , lastName = textField_lastName.getText();
        if (firstName.equals(""))
            showMessage("Error","firstName is empty");
        else if(lastName.equals(""))
            showMessage("Error","lastName is empty");
        else if(email.equals(""))
            showMessage("Error","email is empty");
        else if(password.equals(""))
            showMessage("Error","password is empty");
        else if (DataBase.findEmail(email)!=null)
            showMessage("Error","Email is available");
        else if (password.length()<8)
            showMessage("Error","The length of the password is less than the limit.");
        else {
            Account account = new Account(firstName , lastName , email+"@Custom.ir" , password);
            Email email1 = new Email(account.getEmail(),account.getEmail(),"title","text",new Date());
            account.emails.add(email1.getID());
            DataBase.emails.add(email1);
            DataBase.accounts.add(account);
            DataBase.saveAccountsToFile();
            showMessage("Info","Registration was successful.");
            nextPage("panelMain.fxml",account);
        }
    }
    public void nextPage(String nameFile , Account account) throws IOException {
        Stage stage = (Stage) textField_firstName.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource(nameFile));
        Scene scene = new Scene(parent);
        stage.setUserData(account);
        stage.setScene(scene);
        stage.show();
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
}
