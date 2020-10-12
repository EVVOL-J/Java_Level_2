package Lesson4.model;

import javafx.beans.property.ListProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.ObservableList;

import java.util.ArrayList;
import java.util.List;

public class PersonMail {
    private static final String DEFAULT_MESSAGE = "Hello!!!";


    private final StringProperty name;
    private final StringProperty email;


    public PersonMail(String name, String email) {
        this.name = new SimpleStringProperty(name);
        this.email = new SimpleStringProperty(email);

    }


    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getEmail() {
        return email.get();
    }

    public StringProperty emailProperty() {
        return email;
    }

    public void setEmail(String email) {
        this.email.set(email);
    }


}

