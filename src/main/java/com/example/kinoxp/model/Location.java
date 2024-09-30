package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
   private int id;

    @Column(nullable = false)
    private String name;

    @OneToMany(mappedBy = "location", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Cinema> cinema; //Dette ændrer cinema til en liste, så du kan have flere Cinema-er knyttet til en Location.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public List<Cinema> getCinema() {
        return cinema;
    }

    public void setCinema(List<Cinema> cinema) {
        this.cinema = cinema;
    }
}
