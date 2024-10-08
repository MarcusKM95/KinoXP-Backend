package com.example.kinoxp.controllers;


import com.example.kinoxp.dto.MovieDTO;
import com.example.kinoxp.dto.ShowtimeDTO;
import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Showtime;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.repositories.ShowTimeRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("movies")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    ShowTimeRepository showTimeRepository;


    @GetMapping("{movieId}/showTimes")
    public ResponseEntity<List<Showtime>> getShowTimesForMovie(@PathVariable int movieId) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        List<Showtime> showTimes = showTimeRepository.findByMovie(movie);
        return ResponseEntity.ok(showTimes);
    }

    @PostMapping("/uploadPoster")
    public ResponseEntity<String> uploadPoster(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Define the path where the image should be saved
            String staticImagesPath = new File("src/main/resources/static/images").getAbsolutePath();
            File destinationFile = new File(staticImagesPath, file.getOriginalFilename());

            // Save the file
            file.transferTo(destinationFile);

            // Generate the URL where the image will be accessible
            String imageUrl = "/images/" + file.getOriginalFilename();

            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload file");
        }
    }

    @PostMapping("/create")
    public ResponseEntity<Movie> createMovie(@RequestBody MovieDTO movieDTO) {
        Movie movie = new Movie();
        movie.setMovieName(movieDTO.getTitle());
        movie.setBasePrice(movieDTO.getBasePrice());
        movie.setIs3D(movieDTO.isIs3D());
        movie.setAllNighter(movieDTO.isAllNighter());
        movie.setDurationMinutes(movieDTO.getDurationMinutes());
        movie.setReleaseDate(movieDTO.getReleaseDate());
        movie.setAgeLimit(movieDTO.getAgeLimit());
        movie.setMovieInstructor(movieDTO.getInstructor());
        movie.setGenre(movieDTO.getGenre());
        movie.setPosterURL(movieDTO.getPosterURL());
        Movie savedMovie = movieRepository.save(movie); // Save the movie first to get the ID

        // Optionally: Create default showtimes for this movie
        /*ShowtimeDTO defaultShowtime = new ShowtimeDTO();
        defaultShowtime.setShowTimeAndDate(LocalDateTime.now().plusDays(1)); // For example, tomorrow
        defaultShowtime.set3D(movieDTO.isIs3D());
        defaultShowtime.setFullLength(movie.getDurationMinutes() >= 170);
        defaultShowtime.setMovieId(savedMovie.getId()); // Link to the saved movie

        Showtime showtime = new Showtime();
        showtime.setShowTimeAndDate(defaultShowtime.getShowTimeAndDate());
        showtime.setIs3D(defaultShowtime.is3D());
        showtime.setFullLength(defaultShowtime.isFullLength());
        showtime.setMovie(savedMovie); // Link the showtime to the movie

        showTimeRepository.save(showtime); // Save the showtime*/

        return ResponseEntity.ok(savedMovie);
    }

    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();  // Fetch all movies from the repository

        // Convert each Movie entity to MovieDTO
        List<MovieDTO> movieDTOs = movies.stream().map(this::convertToMovieDTO).toList();

        return ResponseEntity.ok(movieDTOs);  // Return the list of MovieDTOs
    }

    // Helper method to convert Movie to MovieDTO
    private MovieDTO convertToMovieDTO(Movie movie) {
        MovieDTO movieDTO = new MovieDTO();
        movieDTO.setTitle(movie.getMovieName());
        movieDTO.setBasePrice(movie.getBasePrice());
        movieDTO.setIs3D(movie.isIs3D());
        movieDTO.setAllNighter(movie.isAllNighter());
        movieDTO.setDurationMinutes(movie.getDurationMinutes());
        movieDTO.setReleaseDate(movie.getReleaseDate());
        movieDTO.setAgeLimit(movie.getAgeLimit());
        movieDTO.setInstructor(movie.getMovieInstructor());
        movieDTO.setGenre(movie.getGenre());

        // Set the poster URL based on the movie name, removing spaces for file names
        String fileName = movie.getMovieName().replace(" ", "");  // Removes spaces for file names
        movieDTO.setPosterURL("http://localhost:8080/images/" + fileName + ".jpg");

        return movieDTO;
}
    @Transactional
    @DeleteMapping("/{movieName}")
    public ResponseEntity<Void> deleteMovieByName(@PathVariable String movieName) {
        System.out.println("Deleting movie with name: " + movieName);

        String trimmedMovieName = movieName.trim();
        try {
            if (movieRepository.existsByMovieName(trimmedMovieName)) {
                movieRepository.deleteByMovieNameIgnoreCase(trimmedMovieName);
                System.out.println("Movie deleted successfully");
                return ResponseEntity.ok().build();
            } else {
                System.out.println("Movie not found: " + trimmedMovieName);
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace(); // Log the exception details to see the exact error
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @PostMapping("/{movieId}/showtimes")
    public ResponseEntity<Showtime> createShowtime(@PathVariable int movieId, @RequestBody ShowtimeDTO showtimeDTO) {
        Movie movie = movieRepository.findById(movieId)
                .orElseThrow(() -> new RuntimeException("Movie not found"));

        Showtime showtime = new Showtime();
        showtime.setShowTimeAndDate(showtimeDTO.getShowTimeAndDate());
        showtime.setIs3D(showtimeDTO.is3D());
        showtime.setMovie(movie); // Link the showtime to the movie

        Showtime savedShowtime = showTimeRepository.save(showtime);
        return ResponseEntity.ok(savedShowtime);
    }
}


