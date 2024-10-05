package com.example.kinoxp.dto;

import java.util.List;

public class ReservationDTO {

    private Integer userId; // ID for den bruger, der laver reservationen
    private int movieId; // ID for den valgte film
    private int theaterId; // ID for den valgte teater
    private List<Integer> seatIds; // Liste over sæde-ID'er
    private int rowId; // ID for den valgte række
    private int cinemaId; // ID for den valgte biograf
    private int locationId; // ID for den valgte lokation
    private int showTimeId; // ID for den valgte visning
    private double totalPrice; // Total pris for reservationen

    public ReservationDTO() {

    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public int getTheaterId() {
        return theaterId;
    }

    public void setTheaterId(int theaterId) {
        this.theaterId = theaterId;
    }

    public List<Integer> getSeatIds() {
        return seatIds;
    }

    public void setSeatIds(List<Integer> seatIds) {
        this.seatIds = seatIds;
    }

    public int getRowId() {
        return rowId;
    }

    public void setRowId(int rowId) {
        this.rowId = rowId;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }

    public int getShowTimeId() {
        return showTimeId;
    }

    public void setShowTimeId(int showTimeId) {
        this.showTimeId = showTimeId;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
