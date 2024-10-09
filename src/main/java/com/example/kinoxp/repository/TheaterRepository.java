package com.example.kinoxp.repository;

import com.example.kinoxp.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface TheaterRepository extends JpaRepository<Theater, Integer> {

    List<Theater> findTheatersByName(String name);
   // List<Theater> findByName(String name);
    List<Theater> findByLocation_Address(String address);
}
