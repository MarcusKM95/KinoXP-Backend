package com.example.kinoxp.service;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    // Create a new movie
    public Movie createMovie(Movie movie) {
        return movieRepository.save(movie);
    }

    // Retrieve all movies
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    // Retrieve a movie by ID
    public Movie getMovieById(int id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.orElse(null);  // Return null or handle it accordingly
    }

    // Update a movie
    public Movie updateMovie(int id, Movie movieDetails) {
        Movie movie = getMovieById(id);
        if (movie != null) {
            movie.setMovieName(movieDetails.getMovieName());
            movie.setReleaseDate(movieDetails.getReleaseDate());
            movie.setDuration(movieDetails.getDuration());
            movie.setMovieInstructor(movieDetails.getMovieInstructor());
            movie.setAgeLimit(movieDetails.getAgeLimit());
            movie.setVersion(movieDetails.isVersion());
            movie.setPrice(movieDetails.getPrice());
            movie.setGenre(movieDetails.getGenre());
            return movieRepository.save(movie);
        }
        return null;  // Return null if movie not found
    }

    // Delete a movie by ID
    public void deleteMovie(int id) {
        movieRepository.deleteById(id);
    }
}
