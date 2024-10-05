package com.example.kinoxp.dto;

import com.example.kinoxp.model.Theater;

import java.io.Serializable;

public class TheaterDTO implements Serializable {
    private static final long serialVersionUID = 1L; // Tilføj serialVersionUID

    private int id;
    private String name;
    private double basePrice;
    private double cowboyRowDiscount;
    private double sofaRowPremium;
    private int totalRows;

    // Standardkonstruktør
    public TheaterDTO() {
    }

    // Konstruktør
    public TheaterDTO(int id, String name, double basePrice, double cowboyRowDiscount, double sofaRowPremium, int totalRows) {
        this.id = id;
        this.name = name;
        this.basePrice = basePrice;
        this.cowboyRowDiscount = cowboyRowDiscount;
        this.sofaRowPremium = sofaRowPremium;
        this.totalRows = totalRows;
    }

    // Gettere
    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public double getCowboyRowDiscount() {
        return cowboyRowDiscount;
    }

    public double getSofaRowPremium() {
        return sofaRowPremium;
    }

    public int getTotalRows() {
        return totalRows;
    }

    // Factory-metode til at konvertere fra Theater til TheaterDTO
    public static TheaterDTO fromTheater(Theater theater) {
        return new TheaterDTO(
                theater.getId(),
                theater.getTheaterName(),
                theater.getBasePrice(),
                theater.getCowboyRowDiscount(),
                theater.getSofaRowPremium(),
                theater.getTotalRows()
        );
    }
}
