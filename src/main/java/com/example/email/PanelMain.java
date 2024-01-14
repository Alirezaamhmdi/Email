package com.example.email;

import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.util.ArrayList;

public class PanelMain {
    @FXML
    TextField textField_name, textField_email;
    Account account;
    @FXML
    TableView<Email> tableView;
    @FXML
    TableColumn column_number, column_email, column_title, column_date;
    @FXML
    ComboBox<String> comboBox;
    ArrayList<Email> allEmail ;
    public void initialize() {
        comboBox.getItems().addAll(
            "Emails received",
            "Emails sent",
            "Emails read",
            "Unread emails",
            "Important emails"
        );
        comboBox.setValue("Emails received");
        new Thread(() -> {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Stage stage = (Stage) textField_email.getScene().getWindow();
            account = (Account) stage.getUserData();
            textField_name.setText(account.getFullName());
            textField_email.setText(account.getEmail());
            allEmail = DataBase.getEmails(account.emails);
            initializeTable(finEmails(comboBox.getValue()));
        }).start();
        comboBox.setOnAction(actionEvent -> {
            initializeTable(finEmails(comboBox.getValue()));
        });
    }
    public void initializeTable(ArrayList<Email> emails){
        if (comboBox.getValue().equals("Emails sent"))
            column_email.setCellValueFactory(new PropertyValueFactory<>("receiver"));
        else
            column_email.setCellValueFactory(new PropertyValueFactory<>("sender"));
        column_email.setStyle("-fx-alignment: CENTER;");

        column_title.setCellValueFactory(new PropertyValueFactory<>("title"));
        column_title.setStyle("-fx-alignment: CENTER;");


        column_date.setCellValueFactory(new PropertyValueFactory<>("date"));
        column_date.setStyle("-fx-alignment: CENTER;");


        column_number.setCellValueFactory((Callback<TableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>) p ->
                new ReadOnlyObjectWrapper(Integer.parseInt(tableView.getItems().indexOf(p.getValue()) + "") + 1));
        column_number.setStyle("-fx-alignment: CENTER;");

        tableView.setEditable(false);
        ObservableList<Email> sent = FXCollections.observableArrayList(emails);
        tableView.setItems(sent);
    }
    public void exit() throws IOException {
        nextPage("panelLogin.fxml");
    }
    public void sendEmail() throws IOException {
        nextPage("panelSendEmail.fxml");
    }

    public void nextPage(String nameFile) throws IOException {
        Stage stage = (Stage) textField_email.getScene().getWindow();
        Parent parent = FXMLLoader.load(getClass().getResource(nameFile));
        Scene scene = new Scene(parent);
        stage.setScene(scene);
        stage.show();
    }
    public void clickItemTable(MouseEvent mouseEvent) throws IOException {
        if (mouseEvent.getButton().equals(MouseButton.PRIMARY)) {
            if (mouseEvent.getClickCount() == 2 && !tableView.getSelectionModel().isEmpty()) {
                Email email = tableView.getSelectionModel().getSelectedItem();
                DataBase.courentAccount = account;
                showEmail(email);
            }
        }
    }
    public void showEmail(Email email) throws IOException {
        Stage stage = FXMLLoader.load(getClass().getResource("panelShowEmail.fxml"));
        stage.setTitle("Add Contact");
        stage.setUserData(email);
        stage.show();
    }
    public ArrayList<Email> finEmails(String type){
        ArrayList<Email> list = new ArrayList<>();
        for (Email e:allEmail) {
            if (type.equals("Emails received") && e.getReceiver().equals(account.getEmail()))
                list.add(e);
            else if (type.equals("Emails sent") && e.getSender().equals(account.getEmail()))
                list.add(e);
            else if (type.equals("Emails read") && e.getReceiver().equals(account.getEmail()) && e.isRead())
                list.add(e);
            else if (type.equals("Unread emails") && e.getReceiver().equals(account.getEmail()) && !e.isRead())
                list.add(e);
            else if (type.equals("Important emails")
                    && (e.getReceiver().equals(account.getEmail()) || e.getSender().equals(account.getEmail())) && e.isImportant())
                list.add(e);
        }
        return list;
    }
}
