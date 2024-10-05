package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Cinema;
import com.example.kinoxp.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

   // List<Theater> findById(Integer cinemaId);
}
