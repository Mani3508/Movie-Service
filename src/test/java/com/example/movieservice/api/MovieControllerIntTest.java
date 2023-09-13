package com.example.movieservice.api;

import com.example.movieservice.model.Movie;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.asyncDispatch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class MovieControllerIntTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;
    @Test
    void  givenMovie_whereCreateMovie_thenReturSavedMovie() throws Exception{
        //Given
        Movie movie = new Movie();
        movie.setName("rrr");
        movie.setDirector("ss rajamouli");
        movie.setActors(List.of("Ram", "ntr", "Ajay", "Aliya"));

        //When create movie

      var responce =  mockMvc.perform(post("/movies")
              .contentType(MediaType.APPLICATION_JSON)
              .contentType(objectMapper.writeValueAsString(movie)));


      //Then verify saved Movie
        responce.andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(notNullValue())))
                .andExpect(jsonPath("$.name").value(is(movie.getName())))
                .andExpect(jsonPath("$.director",is(movie.getDirector())))
                .andExpect(jsonPath("$.actors", is(movie.getActors())));




    }

}