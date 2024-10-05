package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface TheaterRepository extends JpaRepository<Theater, Integer> {
    boolean existsByTheaterName(String theaterName);

    Optional<Theater> findByTheaterName(String theaterName); // Changed to Optional<Theater>
}
