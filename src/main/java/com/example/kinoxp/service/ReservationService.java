package com.example.kinoxp.service;

import com.example.kinoxp.dto.ReservationDTO;
import com.example.kinoxp.enums.SeatStatus;
import com.example.kinoxp.exceptions.ReservationException;
import com.example.kinoxp.model.*;
import com.example.kinoxp.repositories.*;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private TheaterRepository theaterRepository;

    @Autowired
    private SeatRepository seatRepository;

    @Autowired
    private ShowTimeRepository showTimeRepository;

    @Autowired
    private PrincingConfigRepository pricingConfigRepository;

    @Autowired
    private TheaterRowRepository theaterRowRepository;

    @Autowired
    private CinemaRepository cinemaRepository;

    @Autowired
    private LocationRepository locationRepository;

    public double calculateTotalPrice(Showtime showtime, List<Seat> seats, PricingConfig pricingConfig, Movie selectedMovie) {
        Movie movie = showtime.getMovie(); // Get the movie from the showtime

        // Check if the selected movie matches the showtime's movie
        if (!movie.equals(selectedMovie)) {
            throw new ReservationException("Selected movie does not match the show's movie.");
        }

        double moviePrice = showtime.calculateTicketPrice(pricingConfig); // Calculate the ticket price for the showtime
        return moviePrice * seats.size();
    }

    // Method to find available seats for a given showtime
    public List<Seat> findAvailableSeats(Integer showtimeId) {
        // Get the showtime object to retrieve the theater and its rows
        Showtime showtime = showTimeRepository.findById(showtimeId)
                .orElseThrow(() -> new ReservationException("Showtime not found"));

        // Get all rows in the theater for the specified showtime
        List<TheaterRow> rows = theaterRowRepository.findAllByTheaterId(showtime.getTheater().getId());

        // Collect available seats from all rows
        List<Seat> availableSeats = rows.stream()
                .flatMap(row -> seatRepository.findAllByTheaterRowId(row.getId()).stream())
                .filter(Seat::isAvailable) // Using the isAvailable method
                .collect(Collectors.toList());

        return availableSeats;
    }

    // Create reservation using ReservationDTO
    public Reservations makeReservation(ReservationDTO reservationDTO) {
        User user = getUser(reservationDTO.getUserId());
        Showtime showtime = getShowtime(reservationDTO.getShowTimeId());
        List<Seat> seats = getAvailableSeats(reservationDTO.getSeatIds());
        Movie selectedMovie = getSelectedMovie(reservationDTO.getMovieId());
        PricingConfig pricingConfig = getPricingConfig();

        double totalPrice = calculateTotalPrice(showtime, seats, pricingConfig, selectedMovie);

        Reservations reservation = createReservation(user, showtime, seats, totalPrice);

        updateSeatStatus(seats, SeatStatus.OCCUPIED); // Mark seats as occupied

        return reservationRepository.save(reservation); // Save the reservation
    }

    // Fetch user
    private User getUser(Integer userId) {
        if (userId != null) {
            return userRepository.findById(userId)
                    .orElseThrow(() -> new ReservationException("User not found"));
        }
        return null; // Guest
    }

    // Fetch showtime
    private Showtime getShowtime(Integer showTimeId) {
        return showTimeRepository.findById(showTimeId)
                .orElseThrow(() -> new ReservationException("Showtime not found"));
    }

    // Fetch available seats
    private List<Seat> getAvailableSeats(List<Integer> seatIds) {
        List<Seat> seats = seatRepository.findAllById(seatIds);
        for (Seat seat : seats) {
            if (seat.getSeatStatus() != SeatStatus.AVAILABLE) {
                throw new ReservationException("Seat " + seat.getId() + " is not available.");
            }
        }
        return seats;
    }

    // Fetch selected movie
    private Movie getSelectedMovie(Integer movieId) {
        return movieRepository.findById(movieId)
                .orElseThrow(() -> new ReservationException("Movie not found"));
    }

    // Fetch pricing configuration
    private PricingConfig getPricingConfig() {
        return pricingConfigRepository.findById(1)
                .orElseThrow(() -> new ReservationException("Pricing config not found"));
    }

    // Create reservation
    private Reservations createReservation(User user, Showtime showtime, List<Seat> seats, double totalPrice) {
        Reservations reservation = new Reservations();
        reservation.setUser(user);
        reservation.setShowtime(showtime);
        reservation.setSeats(seats);
        reservation.setTotalPrice(totalPrice);
        return reservation;
    }

    // Update seat status
    private void updateSeatStatus(List<Seat> seats, SeatStatus status) {
        for (Seat seat : seats) {
            seat.setSeatStatus(status);
            seatRepository.save(seat);
        }
    }

    // Generate ticket
    public String generateTicket(Reservations reservations, ReservationDTO reservationDTO) {
        Theater theater = theaterRepository.findById(reservationDTO.getTheaterId())
                .orElseThrow(() -> new ReservationException("Theater not found"));
        List<Seat> seats = seatRepository.findAllById(reservationDTO.getSeatIds());
        TheaterRow theaterRow = theaterRowRepository.findById(reservationDTO.getRowId())
                .orElseThrow(() -> new ReservationException("Row not found"));
        Showtime showTime = showTimeRepository.findById(reservationDTO.getShowTimeId())
                .orElseThrow(() -> new ReservationException("ShowTime not found"));
        Cinema cinema = cinemaRepository.findById(reservationDTO.getCinemaId())
                .orElseThrow(() -> new ReservationException("Cinema not found"));
        Location location = locationRepository.findById(reservationDTO.getLocationId())
                .orElseThrow(() -> new ReservationException("Location not found"));

        // Generate the ticket with the retrieved information
        StringBuilder ticketBuilder = new StringBuilder();
        ticketBuilder.append("Ticket for: ").append(showTime.getMovie().getMovieName()).append("\n");
        ticketBuilder.append("Cinema: ").append(cinema.getName()).append("\n");
        ticketBuilder.append("Location: ").append(location.getName()).append("\n");
        ticketBuilder.append("Theater: ").append(theater.getTheaterName()).append("\n");
        ticketBuilder.append("Row: ").append(theaterRow.getRowNumber()).append("\n");
        ticketBuilder.append("Seats: ");

        for (Seat seat : seats) {
            ticketBuilder.append(seat.getSeatNumber()).append(" ");
        }

        ticketBuilder.append("\nTotal Price: ").append(reservations.getTotalPrice()).append(" DKK");

        return ticketBuilder.toString();
    }

    // Reserve seat
    public void reserveSeat(Integer seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ReservationException("Seat not found"));
        seat.setSeatStatus(SeatStatus.RESERVED); // Assuming there is a status for reserved
        seatRepository.save(seat);
    }

    // Confirm reservation
    public void confirmReservation(Integer seatId) {
        Seat seat = seatRepository.findById(seatId)
                .orElseThrow(() -> new ReservationException("Seat not found"));
        seat.setSeatStatus(SeatStatus.OCCUPIED); // Assuming there is a status for occupied
        seatRepository.save(seat);
    }
}
