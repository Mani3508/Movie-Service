package com.example.movieservice.service;

import com.example.movieservice.model.Movie;
import com.example.movieservice.repo.MovieRepositary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class MovieService  {

    @Autowired
    private MovieRepositary movieRepositary;

    //CRUD operations--Create, Read,Update, Delete

    public Movie create (Movie movie) {

        if (movie == null) {
            throw new RuntimeException("Invalid movie");
        }
        return movieRepositary.save(movie);
    }

    public Movie read(Long id){
        return  movieRepositary.findById(id)
                .orElseThrow(()->new RuntimeException("Movie not found"));
    }

    public void  update(Long id,Movie update){
        if(update == null || id == null){
            throw new RuntimeException("Invalid movie");
        }

        //check if exist

        if(movieRepositary.existsById(id)){
            Movie movie = movieRepositary.getReferenceById(id);
            movie.setName(update.getName());
            movie.setDirector((update.getDirector()));
            movie.setActors(update.getActors());
            movieRepositary.save(movie);

        }else{
            throw  new RuntimeException("Movie not found");
        }
    }

    public void delete (Long id){
        if(movieRepositary.existsById(id)){
            movieRepositary.deleteById(id);
        }else{
            throw new RuntimeException("Movie not found");
        }
    }
}
