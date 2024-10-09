package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Theater {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private String name;

    //ManyToOne relation til Location
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "location_id", nullable = false)
    private Location location;

    @Column (nullable = false)
    private int seats; // antal sæder i salen

    @Column (nullable = false, name = "num_rows")
    private int numRows; // antal rækker i salen

    @Column (nullable = false)
    private double price; // pris for en billet i denne sal
    private double cowboyRowDiscount; // rabat for cowboyrækker (de to forreste rækker)

    private double sofaRowPremium; // premium tillæg for sofarækker

    @Column(nullable = false, unique = true, name = "theater_name")
    private String theaterName; // navne på theater, de er unikke

    @OneToMany(mappedBy = "theater", cascade = CascadeType.ALL)
    private List<TheaterRow> theaterRows = new ArrayList<>(); // Initialiserer listen

    @Column(nullable = false)
    private double basePrice; // Basispris for sæder i teateret
    @Column(nullable = false)
    private int totalRows; // Add this field

    @ManyToOne
    @JoinColumn(name = "cinema_id", nullable = false)
    private Cinema cinema; // salen skal tilknyttes til en biograf

    public Theater(String theaterName, double basePrice, double cowboyRowDiscount, double sofaRowPremium, int totalRows) {
        this.theaterName = theaterName;
        this.basePrice = basePrice;
        this.cowboyRowDiscount = cowboyRowDiscount;
        this.sofaRowPremium = sofaRowPremium;
        this.totalRows = totalRows; // Set the total rows
    }

    public Theater() {
    }

    public int getId() {
        return id;
    }

    public void setTotalRows(int totalRows) {
        this.totalRows = totalRows;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getCowboyRowDiscount() {
        return cowboyRowDiscount;
    }

    public void setCowboyRowDiscount(double cowboyRowDiscount) {
        this.cowboyRowDiscount = cowboyRowDiscount;
    }

    public double getSofaRowPremium() {
        return sofaRowPremium;
    }

    public void setSofaRowPremium(double sofaRowPremium) {
        this.sofaRowPremium = sofaRowPremium;
    }

    public double getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(double basePrice) {
        this.basePrice = basePrice;
    }

    public String getTheaterName() {
        return theaterName;
    }

    public void setTheaterName(String theaterName) {
        this.theaterName = theaterName;
    }

    public List<TheaterRow> getTheaterRows() {
        return theaterRows;
    }

    public void setTheaterRows(List<TheaterRow> theaterRows) {
        this.theaterRows = theaterRows;
    }

    public Cinema getCinema() {
        return cinema;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    // Metode til at få antallet af rækker i et teater
    public int getTotalRows() {
        return (theaterRows != null) ? theaterRows.size() : 0; // Sikkerhed for null
    }

    public double getPriceForRow(int rowNumber) {
        if (rowNumber <= 2) { // Cowboyrækkerne, hvis række er større eller lig med 2
            return basePrice - cowboyRowDiscount;
        } else if (rowNumber > (getTotalRows() - 2)) { // sofarækker
            return basePrice + sofaRowPremium;
        } else {
            return basePrice; // dette er standardpris for alle de øvrige rækker
        }
    }

    // Ny metode til at finde sæder i teateret
    public List<Seat> getAllSeats() {
        List<Seat> allSeats = new ArrayList<>();
        for (TheaterRow row : theaterRows) {
            allSeats.addAll(row.getSeats()); // Tilføj sæder fra hver række
        }
        return allSeats;
    }

    // Metode til at få sæder i en specifik række
    public List<Seat> getSeatsInRow(int rowNumber) {
        for (TheaterRow row : theaterRows) {
            if (row.getRowNumber() == rowNumber) {
                return row.getSeats();
            }
        }
        return new ArrayList<>(); // Returner tom liste, hvis række ikke findes
    }

    public void setCinema(Cinema cinema) {
        this.cinema = cinema;
    }
}
