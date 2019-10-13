package com.cegeka.java.optional.person;

import java.util.List;
import java.util.Optional;

public class PersonStore {

    private List<Person> persons;

    public PersonStore(List<Person> persons) {
        this.persons = persons;
    }

    public String personNameById(int personId) {
        Optional<Person> optPerson =
            persons.stream()
                .filter(p -> personId == p.getId())
                .findFirst();

        return optPerson.map(Person::getName).orElse("");
    }

    public Optional<Person> personById(int personId) {
        return persons.stream()
            .filter(p -> personId == p.getId())
            .findFirst();
    }
}
