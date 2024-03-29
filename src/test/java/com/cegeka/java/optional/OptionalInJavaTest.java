package com.cegeka.java.optional;

import com.cegeka.java.optional.person.Person;
import com.cegeka.java.optional.person.PersonStore;
import com.cegeka.java.optional.person.exception.OffToRetieException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class OptionalInJavaTest {

    private static final Person JOS = new Person("Jos", 0);
    private static final Person JEF = new Person("Jef", 1);
    private static final LocalDate EPOCH = LocalDate.ofEpochDay(0);
    private PersonStore personStore;

    @BeforeEach
    void setUp() {
        personStore = new PersonStore(asList(JOS, JEF));
    }


    @Test
    void personNameById_givenPersonId_returnsNameForPersonWithId() {
        String actual = personStore.personNameById(0);

        assertThat(actual).isEqualTo("Jos");
    }

    @Test
    void optionalGet_givenEmptyOptional_throwsNoSuchElement() {
        assertThrows(NoSuchElementException.class,
            () -> {
                personStore.personById(-1).get();
            });
    }

    @Test
    void personById_givenPersonId_returnsPerson() {
        Optional<Person> actual = personStore.personById(0);
        assertThat(actual).contains(JOS);
    }

    @Test
    void personById_filter() {
        Optional<Person> optPerson = personStore.personById(0);
        assertThrows(OffToRetieException.class, () -> {
            optPerson
                .map(Person::getName)
                .filter("Wim"::equals)
                .orElseThrow(OffToRetieException::new);
        });
    }

    @Test
    void personById_ifPresent() {
        List<Person> found = new ArrayList<>();
        Optional<Person> optPerson = personStore.personById(0);

        optPerson.ifPresent(found::add);
    }

    @Test
    void personById_flatMap() {
        Optional<Person> optPerson = personStore.personById(0);
        LocalDate actual = optPerson
            .flatMap(Person::getDateOfBirth)
            .orElse(EPOCH);

        assertThat(actual).isEqualTo(EPOCH);
    }

}