package com.example.movieservice.repo;

import com.example.movieservice.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepositary extends JpaRepository<Movie, Long> {
}
