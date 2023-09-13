package com.example.movieservice.api;

import com.example.movieservice.model.Movie;
import com.example.movieservice.service.MovieService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/movies")
@Slf4j
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping("/{id}")
    public  ResponseEntity<Movie> getMovie(@PathVariable Long id){
        Movie movie = movieService.read(id);
        log.info("Return movie with  id: {}",id);
        return ResponseEntity.ok(movie);

    }

    @PostMapping
    public  ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie createMovie = movieService.create(movie);
        log.info("Created movie with  id: {}", createMovie.getId());
        return ResponseEntity.ok(createMovie);
    }

  @PutMapping("/{id}")
    public  void  updateMovie(@PathVariable Long id, @RequestBody Movie movie){
        movieService.update(id, movie);
      log.info("updated movie with  id: {}",id);

  }

  @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id){
        movieService.delete(id);
      log.info("Delete movie with  id: {}",id);
  }

}
