package com.example.kinoxp.repositories;

import com.example.kinoxp.model.Snacks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SnackRepository  extends JpaRepository<Snacks, Integer> {
}
