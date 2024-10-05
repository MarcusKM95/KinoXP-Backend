package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name; // navn på biograf

    @ManyToOne
    @JoinColumn(name = "location_id", nullable = false)
    private Location location; // biograf location


    @OneToMany(mappedBy = "cinema") // en biograf kan have mange sale
    private List<Theater> theaters; // liste af sale i biografen

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

    public List<Theater> getTheaters() {
        return theaters;
    }

    public void setTheaters(List<Theater> theaters) {
        this.theaters = theaters;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
