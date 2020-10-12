package Lesson4;


import Lesson4.controller.ControllerChatMain;
import Lesson4.model.PersonMail;
import Lesson4.repository.PersonRepository;
import Lesson4.repository.impl.TestPersonRepository;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

import javafx.stage.Stage;


public class Chat extends Application {

    public final PersonRepository personRepository;
    public final ObservableList<PersonMail> personMail;
    private static final String CHAT_MENU = "../target/classes/src/main/java/Lesson4/view/ChatMenu.fxml";


    public Chat() {
        this.personRepository = new TestPersonRepository();
        this.personMail = FXCollections.observableArrayList(personRepository.getPersonMailData());
    }

    public ObservableList<PersonMail> getPersonMail() {
        return personMail;
    }


    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Chat.class.getResource(CHAT_MENU));
        Parent root = loader.load();


        ControllerChatMain controller = loader.getController();
        controller.setChatApp(this);
        primaryStage.setTitle("My Chat");
        primaryStage.setScene(new Scene(root, 600, 400));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
