package com.example.kinoxp.dto;

import java.sql.Date;
import java.time.LocalDateTime;

public class ShowtimeDTO {
    private int id;
    private LocalDateTime showTimeAndDate;
    private boolean is3D;
    private boolean isFullLength;
    private int movieId; // reference til film

    // Getters and Setters
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
    }

    public boolean is3D() {
        return is3D;
    }

    public void set3D(boolean is3D) {
        this.is3D = is3D;
    }

    public boolean isFullLength() {
        return isFullLength;
    }

    public void setFullLength(boolean fullLength) {
        isFullLength = fullLength;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }
}
