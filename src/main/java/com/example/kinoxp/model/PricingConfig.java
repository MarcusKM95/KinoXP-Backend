package com.example.kinoxp.model;

import jakarta.persistence.*;

@Entity
public class PricingConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private double extraPriceFor3D;

    @Column(nullable = false)
    private double extraPriceForAllNighterMovie;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExtraPriceFor3D() {
        return extraPriceFor3D;
    }

    public void setExtraPriceFor3D(double extraPriceFor3D) {
        this.extraPriceFor3D = extraPriceFor3D;
    }

    public double getExtraPriceForAllNighterMovie() {
        return extraPriceForAllNighterMovie;
    }

    public void setExtraPriceForAllNighterMovie(double extraPriceForAllNighterMovie) {
        this.extraPriceForAllNighterMovie = extraPriceForAllNighterMovie;
    }


    // den validere priserne
    public boolean isValid() {
        return extraPriceFor3D >= 0 && extraPriceForAllNighterMovie >= 0;
    }
}
