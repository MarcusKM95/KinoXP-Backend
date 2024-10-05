package com.example.kinoxp.model;

import com.example.kinoxp.enums.SeatStatus;
import jakarta.persistence.*;

// Repræsenterer et sæde i en række
// og indeholder information om sæde nummer samt en reference til den række, det tilhører.
@Entity
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int seatNumber; // sædenummeret indenfor en række

    @ManyToOne
    @JoinColumn(name = "theaterRow_id")
    private TheaterRow theaterRow; // hvilken række dette sæde tilhører

    @ManyToOne
    @JoinColumn(name = "reservation_id")
    private Reservations reservation; // reference tilbage til reservation

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private SeatStatus seatStatus = SeatStatus.AVAILABLE; // status for sædet


    // Standard konstruktør
    public Seat() {}

    // Konstruktør
    public Seat(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Seat(int seatNumber, TheaterRow theaterRow) {
        this.seatNumber = seatNumber;
        this.theaterRow = theaterRow;
    }

    public Reservations getReservation() {
        return reservation;
    }

    public void setReservation(Reservations reservation) {
        this.reservation = reservation;
    }

    public SeatStatus getSeatStatus() {
        return seatStatus;
    }

    public void setSeatStatus(SeatStatus seatStatus) {
        this.seatStatus = seatStatus;
    }

    public TheaterRow getTheaterRow() {
        return theaterRow;
    }

    public void setTheaterRow(TheaterRow theaterRow) {
        this.theaterRow = theaterRow;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public boolean isAvailable() {
        return seatStatus == SeatStatus.AVAILABLE; // Tjek om sædet er tilgængeligt
    }

}
