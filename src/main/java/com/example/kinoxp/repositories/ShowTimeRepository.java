package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Showtime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShowTimeRepository extends JpaRepository<Showtime, Integer> {
    List<Showtime> findByMovie(Movie movie);
}
