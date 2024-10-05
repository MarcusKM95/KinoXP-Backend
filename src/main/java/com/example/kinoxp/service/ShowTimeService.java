package com.example.kinoxp.service;

import com.example.kinoxp.dto.ShowtimeDTO;
import com.example.kinoxp.model.Showtime;
import com.example.kinoxp.repositories.ShowTimeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ShowTimeService {


    private final ShowTimeRepository showTimeRepository;

    @Autowired
    public ShowTimeService(ShowTimeRepository showTimeRepository) {
        this.showTimeRepository = showTimeRepository;
    }


    //opret showtime
    public ShowtimeDTO createShowTime(ShowtimeDTO showtimeDTO) {
        Showtime showtime = new Showtime();
        showtime.setShowTimeAndDate(showtimeDTO.getShowTimeAndDate());
        showtime.setIs3D(showtimeDTO.is3D());
        showtime.setFullLength(showtimeDTO.isFullLength());
        showtime.setId(showtimeDTO.getMovieId());

        showtime = showTimeRepository.save(showtime);
        return convertToDTO(showtime);
    }


    // hent en showtime efter id
    public Optional<ShowtimeDTO> getShowtimeById(int id) {
        Optional<Showtime> showtime = showTimeRepository.findById(id);
        return showtime.map(this::convertToDTO);
    }

    // Hent alle showtimes
    public List<ShowtimeDTO> getAllShowtimes() {
        List<Showtime> showtimes = showTimeRepository.findAll();
        return showtimes.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    // Opdater en eksisterende showtime
    public Optional<ShowtimeDTO> updateShowtime(int id, ShowtimeDTO showtimeDTO) {
        Optional<Showtime> existingShowtimeOpt = showTimeRepository.findById(id);
        if (existingShowtimeOpt.isPresent()) {
            Showtime existingShowtime = existingShowtimeOpt.get();
            existingShowtime.setShowTimeAndDate(showtimeDTO.getShowTimeAndDate());
            existingShowtime.setIs3D(showtimeDTO.is3D());
            existingShowtime.setFullLength(showtimeDTO.isFullLength());
            existingShowtime.setId(showtimeDTO.getMovieId());

            existingShowtime = showTimeRepository.save(existingShowtime);
            return Optional.of(convertToDTO(existingShowtime));
        }
        return Optional.empty();
    }

    // Slet en showtime
    public boolean deleteShowtime(int id) {
        if (showTimeRepository.existsById(id)) {
            showTimeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Konvertere en Showtime model til ShowtimeDTO
    private ShowtimeDTO convertToDTO(Showtime showtime) {
        ShowtimeDTO dto = new ShowtimeDTO();
        dto.setId(showtime.getId());
        dto.setShowTimeAndDate(showtime.getShowTimeAndDate());
        dto.set3D(showtime.isIs3D());
        dto.setFullLength(showtime.isFullLength());
        dto.setMovieId(showtime.getId());
        return dto;
    }



}
