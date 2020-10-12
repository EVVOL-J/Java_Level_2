package Lesson4.controller;

import Lesson4.Chat;
import Lesson4.model.PersonMail;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;


public class ControllerChatMain {

    @FXML
    private TableView<PersonMail> personTable;
    @FXML
    private TableColumn<PersonMail, String> nameColumn;
    @FXML
    private TableColumn<PersonMail, String> emailColumn;
    @FXML
    private Button sendButton;
    @FXML
    private TextArea dialog;
    @FXML
    private TextField enterText;
    private Chat chatApp;

    public ControllerChatMain() {

    }

    public void initialize() {
        nameColumn.setCellValueFactory(cellData -> cellData.getValue().nameProperty());
        emailColumn.setCellValueFactory(cellData -> cellData.getValue().emailProperty());
        sendButton.setOnAction(event -> sendMessage());
        enterText.setOnAction(event -> sendMessage());

    }

    private void sendMessage() {
        dialog.appendText(enterText.getText() + "\n");
        enterText.clear();
    }

    public void setChatApp(Chat chatApp) {
        this.chatApp = chatApp;
        personTable.setItems(chatApp.getPersonMail());

    }


}





