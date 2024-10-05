package com.example.kinoxp.repositories;

import com.example.kinoxp.model.TheaterRow;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TheaterRowRepository extends JpaRepository<TheaterRow, Integer> {
    List<TheaterRow> findAllByTheaterId(int id);

    TheaterRow findByIdAndTheaterId(Integer rowId, Integer theaterId);

    List<TheaterRow> findAllByTheaterIdOrderByRowNumberAsc(int theaterId);}
