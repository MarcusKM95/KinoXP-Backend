package com.example.kinoxp.config;

import com.example.kinoxp.enums.Genre;
import com.example.kinoxp.model.*;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.repositories.ShowTimeRepository;
import com.example.kinoxp.repositories.TheaterRepository;
import com.example.kinoxp.repositories.TheaterRowRepository;
import com.example.kinoxp.repositories.CinemaRepository;
import com.example.kinoxp.repositories.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class ReservationData implements CommandLineRunner {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private TheaterRowRepository theaterRowRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public void run(String... args) {
        try {
            // Initialize location data
            Location location = new Location();
            location.setAddress("Copenhagen City Center");
            locationRepository.save(location);

            // Initialize movie data
            List<Movie> movies = new ArrayList<>();
            movies.add(createMovie("Anders And", 181, "Anthony Russo, Joe Russo", 12, true, Genre.ACTION, 120.0));
            movies.add(createMovie("Lion King 3", 118, "Jon Favreau", 0, true, Genre.ADVENTURE, 100.0));

            System.out.println("Saving location: " + location.getAddress());
            locationRepository.save(location);
            System.out.println("Location saved: " + location.getAddress());

            for (Movie movie : movies) {
                System.out.println("Saving movie: " + movie.getMovieName());
                saveMovieIfNotExists(movie);
            }

            // Save movies to database
            for (Movie movie : movies) {
                saveMovieIfNotExists(movie);
            }

            // Initialize cinema data
            Cinema cinema = new Cinema();
            cinema.setName("Main Cinema");
            cinema.setLocation(location);
            cinemaRepository.save(cinema);

            // Initialize theater data
            List<Theater> theaters = new ArrayList<>();
            theaters.add(createTheater("Main Theater", 100.0, 20.0, 30.0, 5, cinema));
            theaters.add(createTheater("VIP Theater", 150.0, 25.0, 50.0, 5, cinema));

            // Save theaters to database
            for (Theater theater : theaters) {
                saveTheaterIfNotExists(theater);
            }

            // Initialize rows and seats for theaters
            for (Theater theater : theaters) {
                List<TheaterRow> rows = new ArrayList<>();
                for (int i = 1; i <= theater.getTotalRows(); i++) {
                    TheaterRow row = new TheaterRow(i, 10);
                    row.setTheater(theater);
                    rows.add(row);
                }
                theaterRowRepository.saveAll(rows);
                theater.setTheaterRows(rows);
                theaterRepository.save(theater);
                for (TheaterRow row : rows) {
                    row.initializeSeats();
                }
            }

            // Initialize showtime data
            List<Showtime> showtimes = new ArrayList<>();
            Movie savedMovie1 = movieRepository.findByMovieName("Anders And").orElse(null);
            Movie savedMovie2 = movieRepository.findByMovieName("Lion King 3").orElse(null);

            showtimes.add(createShowtime(savedMovie1, theaterRepository.findByTheaterName("Main Theater").orElse(null), LocalDateTime.of(2024, 10, 10, 19, 0), true));
            showtimes.add(createShowtime(savedMovie1, theaterRepository.findByTheaterName("Main Theater").orElse(null), LocalDateTime.of(2024, 10, 10, 22, 0), true));
            showtimes.add(createShowtime(savedMovie2, theaterRepository.findByTheaterName("VIP Theater").orElse(null), LocalDateTime.of(2024, 10, 11, 15, 0), false));

            showTimeRepository.saveAll(showtimes);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }

    private Movie createMovie(String name, int duration, String instructor, int ageLimit, boolean is3D, Genre genre, double basePrice) {
        Movie movie = new Movie();
        movie.setMovieName(name);
        movie.setReleaseDate(LocalDate.now());
        movie.setDurationMinutes(duration);
        movie.setAllNighter(false);
        movie.setMovieInstructor(instructor);
        movie.setAgeLimit(ageLimit);
        movie.setIs3D(is3D);
        movie.setGenre(genre);
        movie.setBasePrice(basePrice);
        return movie;
    }

    private void saveMovieIfNotExists(Movie movie) {
        if (!movieRepository.existsByMovieName(movie.getMovieName())) {
            movieRepository.save(movie);
        } else {
            System.out.println("Movie already exists: " + movie.getMovieName());
        }
    }

    private Theater createTheater(String name, double basePrice, double cowboyRowDiscount, double sofaRowPremium, int totalRows, Cinema cinema) {
        Theater theater = new Theater();
        theater.setTheaterName(name);
        theater.setBasePrice(basePrice);
        theater.setCowboyRowDiscount(cowboyRowDiscount);
        theater.setSofaRowPremium(sofaRowPremium);
        theater.setTotalRows(totalRows);
        theater.setCinema(cinema); // Link the theater to the cinema
        return theater;
    }

    private void saveTheaterIfNotExists(Theater theater) {
        if (!theaterRepository.existsByTheaterName(theater.getTheaterName())) {
            theaterRepository.save(theater);
        } else {
            System.out.println("Theater already exists: " + theater.getTheaterName());
        }
    }

    private Showtime createShowtime(Movie movie, Theater theater, LocalDateTime showTimeAndDate, boolean is3D) {
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setTheater(theater);
        showtime.setShowTimeAndDate(showTimeAndDate);
        showtime.setIs3D(is3D);
        showtime.setFullLength(false);
        return showtime;
    }

}
