package Lesson4.repository;

import Lesson4.model.PersonMail;

import java.util.List;

public interface PersonRepository {
    List<PersonMail> getPersonMailData();
}
