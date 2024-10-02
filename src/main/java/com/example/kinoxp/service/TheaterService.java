package com.example.kinoxp.service;

import com.example.kinoxp.model.Theater;
import com.example.kinoxp.repository.TheaterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class TheaterService {
    @Autowired
    private TheaterRepository theaterRepository;

    public Theater createTheater(Theater theater) {
        return theaterRepository.save(theater);
    }
    public List<Theater> getAllTheaters() {
        return theaterRepository.findAll();
    }
    public Theater getTheaterById(int id) {
        Optional<Theater> theater = theaterRepository.findById(id);
        return theater.orElseThrow(() -> new NoSuchElementException("Theater not found with id" + id));
    }
    public Theater updateTheater(int id, Theater theaterDetails) {
        Theater theater = getTheaterById(id);
        theater.setName(theaterDetails.getName());
        theater.setLocation(theaterDetails.getLocation());
        theater.setSeats(theaterDetails.getSeats());
        theater.setNumRows(theaterDetails.getNumRows());
        theater.setPrice(theaterDetails.getPrice());
        theater.setCinema(theaterDetails.getCinema());

        return theaterRepository.save(theater);

    }
    public void deleteTheater(int id) {
        Theater theater = getTheaterById(id);
        theaterRepository.delete(theater);
    }
    public List<Theater> findTheatersByLocationAddress(String address) {
        return theaterRepository.findByLocation_Address(address);
    }

}
