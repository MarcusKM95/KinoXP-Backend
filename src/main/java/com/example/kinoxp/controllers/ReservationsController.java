package com.example.kinoxp.controllers;

import com.example.kinoxp.Response.ResponseMessage;
import com.example.kinoxp.exceptions.ReservationException;
import com.example.kinoxp.model.Reservations;
import com.example.kinoxp.model.Seat;
import com.example.kinoxp.repositories.ReservationRepository;
import com.example.kinoxp.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/reservations")
public class ReservationsController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private ReservationRepository reservationRepository;

    @PostMapping("/book")
    public ResponseEntity<Reservations> bookReservation(@RequestBody Reservations reservation) {
        // Check if seats are already reserved for the same showtime
        for (Seat seat : reservation.getSeats()) {
            List<Reservations> existingReservations = reservationRepository.findByShowtimeAndSeatsContaining(reservation.getShowtime(), seat);
            if (!existingReservations.isEmpty()) {
                throw new ReservationException("Sædet " + seat.getId() + " er allerede reserveret for denne showtime.");
            }
        }
        // Calculate the total price
        reservation.setTotalPrice(reservation.calculateTotalPrice());
        return ResponseEntity.ok(reservationRepository.save(reservation));
    }

    @GetMapping("/available/{showtimeId}")
    public ResponseEntity<List<Seat>> getAvailableSeats(@PathVariable Integer showtimeId) {
        List<Seat> availableSeats = reservationService.findAvailableSeats(showtimeId);
        return ResponseEntity.ok(availableSeats);
    }

    @PostMapping("/confirm/{seatId}")
    public ResponseEntity<ResponseMessage> confirmReservation(@PathVariable Integer seatId) {
        reservationService.confirmReservation(seatId);
        return ResponseEntity.ok(new ResponseMessage("Reservation er bekræftet!"));
    }
}
