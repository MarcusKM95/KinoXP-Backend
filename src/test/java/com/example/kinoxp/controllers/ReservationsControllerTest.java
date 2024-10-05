package com.example.kinoxp.controllers;

import com.example.kinoxp.model.Movie;
import com.example.kinoxp.model.Reservations;
import com.example.kinoxp.model.Theater;
import com.example.kinoxp.model.User;
import com.example.kinoxp.repositories.MovieRepository;
import com.example.kinoxp.repositories.ReservationRepository;
import com.example.kinoxp.repositories.TheaterRepository;
import com.example.kinoxp.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ReservationsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private ReservationRepository reservationsRepository;

    @Mock
    private UserRepository userRepository;

    @Mock
    private MovieRepository movieRepository;

    @Mock
    private TheaterRepository theaterRepository;

    @InjectMocks
    private ReservationsController reservationsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void bookReservation() throws Exception {
        // Arrange - setup data
        User customer = new User();
        customer.setId(1);
        customer.setName("Arfi Jabril");
        customer.setEmail("arfi@example.com");

        Movie movie = new Movie();
        movie.setId(1);
        movie.setMovieName("Inception");

        Theater theater = new Theater();
        theater.setId(1);
        theater.setTheaterName("Theater A");

        Reservations reservation = new Reservations();
        reservation.setUser(customer);
        reservation.setMovie(movie);
        reservation.setTheater(theater);
        reservation.setTotalPrice(120.00);

        when(userRepository.findById(1)).thenReturn(java.util.Optional.of(customer));
        when(movieRepository.findById(1)).thenReturn(java.util.Optional.of(movie));
        when(theaterRepository.findById(1)).thenReturn(java.util.Optional.of(theater));
        when(reservationsRepository.save(any(Reservations.class))).thenReturn(reservation);

        // Act - simulate POST request
        String json = "{ \"userId\": 1, \"movieId\": 1, \"theaterId\": 1, \"totalPrice\": 120.00 }";
        MvcResult result = mockMvc.perform(post("/reservations/book")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(json))
                .andExpect(status().isOk())
                .andReturn();

        // Assert - check the response
        verify(reservationsRepository, times(1)).save(any(Reservations.class));
        assertNotNull(result.getResponse().getContentAsString());
    }
}
