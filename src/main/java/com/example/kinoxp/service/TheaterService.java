package com.example.kinoxp.service;

import com.example.kinoxp.model.Cinema;
import com.example.kinoxp.model.Theater;
import com.example.kinoxp.repositories.CinemaRepository;
import com.example.kinoxp.repositories.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

  /*  public void createTheater() {
       // Cinema cinema = cinemaRepository.findById(cinemaId); // Hent en eksisterende Cinema
        if (cinema != null) {
            Theater theater = new Theater();
            theater.setCinema(cinema);
            theaterRepository.save(theater);
        } else {
            // Håndter situationen, hvor cinema ikke findes
        }
    }*/

}
