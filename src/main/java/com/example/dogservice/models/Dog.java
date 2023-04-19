package com.example.dogservice.models;

import java.util.Objects;

import jakarta.persistence.*;
import lombok.ToString;

@ToString
@Entity
@Table
public class Dog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String breed;


    public Dog() {
    }

    public Dog(Long id, String name, String breed) {
        this.id = id;
        this.name = name;
        this.breed = breed;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Dog id(Long id) {
        setId(id);
        return this;
    }

    public Dog name(String name) {
        setName(name);
        return this;
    }

    public Dog breed(String breed) {
        setBreed(breed);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Dog)) {
            return false;
        }
        Dog dog = (Dog) o;
        return Objects.equals(id, dog.id) && Objects.equals(name, dog.name) && Objects.equals(breed, dog.breed);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, breed);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", name='" + getName() + "'" +
            ", breed='" + getBreed() + "'" +
            "}";
    }

}
