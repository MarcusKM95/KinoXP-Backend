package com.example.kinoxp.model;

import jakarta.persistence.*;

@Entity
public class Reservations {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private  Theater theater;

    @OneToOne
    @JoinColumn(name = "snacks_id")
    private Snacks snacks;

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema; // mange reservationer kan have en biograf

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location; // mange reservationer kan have en location

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public Snacks getSnacks() {
        return snacks;
    }

    public void setSnacks(Snacks snacks) {
        this.snacks = snacks;
    }
}
