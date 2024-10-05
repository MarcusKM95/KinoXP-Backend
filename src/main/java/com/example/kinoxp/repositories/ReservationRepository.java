package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Reservations;
import com.example.kinoxp.model.Seat;
import com.example.kinoxp.model.Showtime;
import com.example.kinoxp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservations, Integer> {
    List<Reservations> findByUser(User user);
    List<Reservations> findByShowtimeAndSeatsContaining(Showtime showtime, Seat seat);

}
