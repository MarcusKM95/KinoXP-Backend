package com.example.kinoxp.model;

import com.example.kinoxp.enums.Genre;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false, unique = true)
    private String movieName;

    @Column(nullable = false)
    private LocalDate releaseDate;

    @Column(name = "duration", nullable = false)
    private int durationMinutes;

    private boolean isAllNighter;  // Additional charge for long movies


    @Column(nullable = false)
    private String movieInstructor;

    @Column(nullable = false)
    private int ageLimit;

    @Column(nullable = false)
    private boolean is3D;

    @Enumerated(EnumType.STRING)
    private Genre genre;

    @Column(nullable = false)
    private double basePrice; // grundpris for filmen

    @OneToMany(mappedBy = "movie")
    private List<Showtime> showTimes; // en film kan have mange showTimes

    @Column
    private String posterURL;

    public String getPosterURL() {
        return posterURL;
    }

    public void setPosterURL(String posterURL) {
        this.posterURL = posterURL;
    }

    // Standard konstruktør
    public Movie() {}


    // Konstruktør
    public Movie(String movieName) {
        this.movieName = movieName;
    }

    public boolean isAllNighter() {
        return isAllNighter;
    }

    public void setAllNighter(boolean allNighter) {
        isAllNighter = allNighter;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Showtime> getShowTimes() {
        return showTimes;
    }

    public void setShowTimes(List<Showtime> showTimes) {
        this.showTimes = showTimes;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public String getMovieInstructor() {
        return movieInstructor;
    }

    public void setMovieInstructor(String movieInstructor) {
        this.movieInstructor = movieInstructor;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }



}
