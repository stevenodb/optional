package com.cegeka.java.optional.person;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import java.time.LocalDate;
import java.util.Optional;

public class Person {

    private final int id;
    private final String name;
    private LocalDate dateOfBirth;

    public Person(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public Optional<LocalDate> getDateOfBirth() {
        return Optional.ofNullable(dateOfBirth);
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return new EqualsBuilder()
            .append(id, person.id)
            .append(name, person.name)
            .append(dateOfBirth, person.dateOfBirth)
            .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
            .append(name)
            .append(dateOfBirth)
            .append(id)
            .toHashCode();
    }
}
