package com.example.proekt.model;

import javax.persistence.*;

@Entity
public class Director {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String surname;

    public Director() {
    }

    public Director(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }




    public String toString() {
        Long var10000 = this.getId();
        return "Director(id=" + var10000 + ", name=" + this.getName() + ", surname=" + this.getSurname() + ")";
    }
}
