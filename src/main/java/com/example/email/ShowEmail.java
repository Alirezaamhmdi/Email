package com.example.email;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class ShowEmail {
    @FXML
    Stage stage;
    @FXML
    TextField textField_email , textField_title , textField_date;
    @FXML
    TextArea textArea_text;
    Email email;
    @FXML
    CheckBox checkBox_important;
    Account account;
    public void initialize(){
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            account = DataBase.courentAccount;
            Stage stage = (Stage) textField_email.getScene().getWindow();
            email = (Email) stage.getUserData();
            textField_email.setText(email.getDestinationEmail(account.getEmail()));
            textField_title.setText(email.getTitle());
            textArea_text.setText(email.getText());
            textField_date.setText(email.getDate().toString());
            email.setRead(true);
            checkBox_important.setSelected(email.isImportant());
        }).start();
    }
    public void close(){
        stage.close();
    }
    public void checkBoxAction() throws IOException {
        email.setImportant(checkBox_important.isSelected());
        DataBase.saveEmailsToFile();
    }
}
