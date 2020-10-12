package Lesson4.repository.impl;

import Lesson4.model.PersonMail;
import Lesson4.repository.PersonRepository;

import java.util.List;

public class TestPersonRepository implements PersonRepository {

    @Override
    public List<PersonMail> getPersonMailData() {
        return List.of(
                new PersonMail("Hans", "Muster@mail.ru"),
                new PersonMail("Ruth", "Mueller@mail.ru"),
                new PersonMail("Heinz", "Kurz@mail.ru"),
                new PersonMail("Cornelia", "Meier@mail.ru")

        );
    }
}
