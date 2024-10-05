package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"showtime_id", "seat_id"}))
public class Reservations {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int id;
    private double totalPrice;

    @ManyToOne
    @JoinColumn (name = "showtime_id")
    private Showtime showtime;

    @ManyToOne
    @JoinColumn (name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn (name = "movie_id")
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id")
    private  Theater theater;

    @ManyToOne
    @JoinColumn(name = "row_id")
    private TheaterRow theaterRow;

    @OneToMany
    @JoinColumn(name = "snacks_id")
    private List<Snacks>snacks; // flere snacks pr. kunde

    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema; // mange reservationer kan have en biograf

    @ManyToOne
    @JoinColumn(name = "location_id")
    private Location location; // mange reservationer kan have en location

    @OneToMany(mappedBy = "reservation")
    private List<Seat> seats; // liste af Sæderne der er reserveret

    @Temporal(TemporalType.TIMESTAMP)
    private Date reservationDate; // Tilføj dette felt


    public Reservations() {

    }
    public Reservations(User user, Showtime showtime, List<Seat> seats, double totalPrice) {
        this.user = user;
        this.showtime = showtime;
        this.seats = seats;
        this.totalPrice = totalPrice;
        this.reservationDate = new Date(); // Gem nuværende dato for reservation
    }

    public Showtime getShowtime() {
        return showtime;
    }

    public void setShowtime(Showtime showtime) {
        this.showtime = showtime;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public void setSeats(List<Seat> seats) {
        this.seats = seats;
        this.totalPrice = calculateTotalPrice(); // Recalculate total price when seats are set

    }

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

    public List<Snacks> getSnacks() {
        return snacks;
    }

    public void setSnacks(List<Snacks> snacks) {
        this.snacks = snacks;
    }

    public TheaterRow getTheaterRow() {
        return theaterRow;
    }

    public void setTheaterRow(TheaterRow theaterRow) {
        this.theaterRow = theaterRow;
    }

    public double calculateTotalPrice() {
        double total = 0.0;
        for (Seat seat : seats) {
            int rowNumber = seat.getTheaterRow().getRowNumber(); // Antag at Seat har en reference til TheaterRow
            double priceForRow = theater.getPriceForRow(rowNumber); // Få prisen for rækken
            total += priceForRow;
        }
        return total;
    }
}
