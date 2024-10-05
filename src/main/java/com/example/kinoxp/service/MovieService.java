package com.example.kinoxp.service;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Showtime;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.repositories.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    @Autowired
    MovieRepository movieRepository;

    @Autowired
    ShowTimeRepository showTimeRepository;


    public void showShowTimeForThisMove(Movie movie) {

        List<Showtime> availableShowTimes = movie.getShowTimes();


        if (availableShowTimes == null || availableShowTimes.isEmpty()) {
            System.out.println("No available showTimes for this movie.");
            return;
        }

        for (Showtime showtime : availableShowTimes) {
            System.out.println(showtime.getShowTimeAndDate());
        }
    }

}
