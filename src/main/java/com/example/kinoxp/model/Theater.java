package com.example.kinoxp.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Theater {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;

    @Column (nullable = false)
    private int seats; // antal sæder i salen

    @Column (nullable = false, name = "num_rows")
    private int numRows; // antal rækker i salen

    @Column (nullable = false)
    private double price; // pris for en billet i denne sal

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema; // salen skal tilknyttets til en biograf

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }


    public int getNumRows() {
        return numRows;
    }

    public void setNumRows(int numRows) {
        this.numRows = numRows;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
