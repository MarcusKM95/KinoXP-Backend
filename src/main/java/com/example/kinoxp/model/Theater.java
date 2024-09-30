package com.example.kinoxp.model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.GetMapping;

@Entity
public class Theater {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column (nullable = false)
    private int seats;
    @Column (nullable = false)

    private int rows;
    @Column (nullable = false)

    private double price;

    public int getSeats() {
        return seats;
    }

    public void setSeats(int seats) {
        this.seats = seats;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
