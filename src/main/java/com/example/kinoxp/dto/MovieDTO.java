package com.example.kinoxp.dto;

import com.example.kinoxp.enums.Genre;

import java.time.LocalDate;

public class MovieDTO {
    private String title;
    private double basePrice;
    private boolean is3D;
    private boolean isAllNighter;
    private int durationMinutes;
    private LocalDate releaseDate;  // Release date of the movie
    private int ageLimit;  // Minimum age to watch the movie
    private String instructor;  // Movie instructor (director)
    private Genre genre;  // Enum for genre


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public boolean isIs3D() {
        return is3D;
    }

    public void setIs3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isAllNighter() {
        return isAllNighter;
    }

    public void setAllNighter(boolean allNighter) {
        isAllNighter = allNighter;
    }

    public int getDurationMinutes() {
        return durationMinutes;
    }

    public void setDurationMinutes(int durationMinutes) {
        this.durationMinutes = durationMinutes;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public String getInstructor() {
        return instructor;
    }

    public void setInstructor(String instructor) {
        this.instructor = instructor;
    }

    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
