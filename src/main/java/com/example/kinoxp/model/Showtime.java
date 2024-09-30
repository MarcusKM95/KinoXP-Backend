package com.example.kinoxp.model;

import jakarta.persistence.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Showtime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = false)
    private LocalDateTime endTime;


    // Dette giver mulighed for at tilføje flere ugedage til en enkelt Showtime.
    //Du kan tilføje flere ugedage for den samme filmvisning. Dette er nyttigt, hvis filmen skal vises flere gange om ugen (f.eks. mandag, onsdag og fredag).
   ///Du skal muligvis tilføje valideringslogik for at sikre, at det kun er gyldige ugedage, der bliver tilføjet.
    @ElementCollection
    private List<DayOfWeek> dayOfWeeks; //Liste over ugedage, hvor filmen spilles


    @ManyToOne
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

    public LocalDateTime getStartTime() {
        return startTime;
    }

    // dette sikre at end time altid er efter start time
    public void setStartTime(LocalDateTime startTime) {
        if (endTime != null && endTime.isBefore(startTime)) {
            throw  new IllegalArgumentException("End time must be after end time");
        }
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    // dette sikre at end time altid er efter start time
    public void setEndTime(LocalDateTime endTime) {
        if (startTime != null && endTime.isBefore(startTime)) {
            throw  new IllegalArgumentException("End time must be after start time");
        }
        this.endTime = endTime;
    }

    public List<DayOfWeek> getDayOfWeeks() {
        return dayOfWeeks;
    }

    public void setDayOfWeeks(List<DayOfWeek> dayOfWeeks) {
        this.dayOfWeeks = dayOfWeeks;
    }
}
