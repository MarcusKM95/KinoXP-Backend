package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MovieRepository  extends JpaRepository<Movie, Integer> {
    Optional<Movie> findByMovieName(String movieName);
    boolean existsByMovieName(String movieName); // New method to check if a movie exists by name


    void deleteByMovieNameIgnoreCase(String movieName);
}
