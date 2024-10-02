package com.example.kinoxp.controller;

import com.example.kinoxp.model.Theater;
import com.example.kinoxp.service.TheaterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/theaters")
public class TheaterController {

    @Autowired
    private TheaterService theaterService;

    @PostMapping("create")
    public ResponseEntity<Theater> createTheater(@RequestBody Theater theater) {
        Theater createdTheater = theaterService.createTheater(theater);
        return ResponseEntity.ok(createdTheater);
    }
    @GetMapping
    public ResponseEntity<List<Theater>> getTheaters() {
        List<Theater>theaters = theaterService.getAllTheaters();
        return ResponseEntity.ok(theaters);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Theater> getTheater(@PathVariable int id) {
        Theater theater = theaterService.getTheaterById(id);
        return ResponseEntity.ok(theater);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Theater> updateTheater(@PathVariable int id, @RequestBody Theater theaterDetails){
        Theater theater = theaterService.updateTheater(id, theaterDetails);
        return ResponseEntity.ok(theater);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteTheater(@PathVariable int id) {
        theaterService.deleteTheater(id);
        return ResponseEntity.noContent().build();

    }

}
