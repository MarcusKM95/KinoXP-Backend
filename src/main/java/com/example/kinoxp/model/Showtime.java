package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.sql.Date;
import java.time.LocalDateTime;

@Entity
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @Column(nullable = false)
   private LocalDateTime showTimeAndDate;

    @Column (nullable = false)
    private boolean is3D;

    @Column (nullable = false)
    private boolean isFullLength;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "movie_id", nullable = false)
    private Movie movie;

    @ManyToOne
    @JoinColumn(name = "theater_id", nullable = false)
    private Theater theater;

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
        updateFullLengthStatus(); // opdater status når film sættes
    }

    public Theater getTheater() {
        return theater;
    }

    public void setTheater(Theater theater) {
        this.theater = theater;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDateTime getShowTimeAndDate() {
        return showTimeAndDate;
    }

    public void setShowTimeAndDate(LocalDateTime showTimeAndDate) {
        this.showTimeAndDate = showTimeAndDate;
        updateFullLengthStatus(); // opdater status når tid sættes
    }

    public boolean isIs3D() {
        return is3D;
    }


    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isFullLength() {
        return isFullLength;
    }

    public void setFullLength(boolean fullLength) {
        isFullLength = fullLength;
    }

    // Metode til at opdatere isFullLength baseret på filmens varighed
    private void updateFullLengthStatus() {
        if (movie != null) {
            this.isFullLength = movie.getDurationMinutes() >= 170; // F.eks. mere end 2 timer
        }
    }

    // Metode til at beregne sluttiden for showet
    public LocalDateTime calculateEndTime() {
        if (movie != null) {
            return showTimeAndDate.plusMinutes(movie.getDurationMinutes());
        }
        return null; // Hvis filmen ikke er sat, kan du returnere null eller kaste en undtagelse
    }

    public double calculateTicketPrice(PricingConfig config) {
        double price = movie.getBasePrice(); // Hent basisprisen fra Movie
        if (is3D) price += config.getExtraPriceFor3D(); // Ekstra tillæg for 3D
        if (isFullLength) price += config.getExtraPriceForAllNighterMovie(); // Ekstra tillæg for hele aftensfilm
        return price;
    }
}
