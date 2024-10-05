package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.TheaterRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SeatRepository extends JpaRepository<Seat, Integer> {
    // Returtypen skal være Optional<Seat>
    Optional<Seat> findByTheaterRowAndSeatNumber(TheaterRow theaterRow, int seatNumber);
    List<Seat> findAllByTheaterRowId(Integer theaterRowId);

}
