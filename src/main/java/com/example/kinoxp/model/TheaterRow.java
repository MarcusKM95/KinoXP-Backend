package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.List;
//Repræsenterer en række i en teatersal,
// og indeholder en liste af Seat objekter.
// Hver række tilhører et Theater.
@Entity
@Table(name = "theater_row")
public class TheaterRow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int numSeatsPerRow; // Antallet af sæder i denne række


    @Column(name = "row_number_value",nullable = false)
    private int rowNumber; // række nummer (f.eks 1, 2, 3, osv..)

    @OneToMany(mappedBy = "theaterRow", cascade = CascadeType.ALL)
    private List<Seat> seats; // liste af sæder i rækken

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private Theater theater; // reference til teateret

    public TheaterRow() {
    }

    public TheaterRow(int rowNumber, int numSeatsPerRow) {
        this.rowNumber = rowNumber;
        this.numSeatsPerRow = numSeatsPerRow;
        initializeSeats(); // Automatisk oprettelse af sæder
    }

    public int getNumSeatsPerRow() {
        return numSeatsPerRow;
    }

    public void setNumSeatsPerRow(int numSeatsPerRow) {
        this.numSeatsPerRow = numSeatsPerRow;
        initializeSeats(); // Opdatere sæder, hvis antallet ændres
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    // Metode til at oprette sæder for rækken
    public void initializeSeats() {
        seats.clear(); // Fjern eksisterende sæder, hvis nogen

        for (int seatNumber = 1; seatNumber <= numSeatsPerRow; seatNumber++) {
            Seat seat = new Seat(seatNumber, this); // Opret sædet og knyt det til rækken
            seats.add(seat);
        }
    }
}
