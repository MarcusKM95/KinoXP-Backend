package com.example.kinoxp.controller;

import com.example.kinoxp.model.Location;
import com.example.kinoxp.service.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    //opret en ny location
    @PostMapping("/create")
    public ResponseEntity<Location>createLocation(@RequestBody Location location) {
        Location createdLocation = locationService.createLocation(location);
        return ResponseEntity.ok(createdLocation);
    }
//Hent alle locations
    @GetMapping
    public ResponseEntity<List<Location>> getAllLocations() {
        List<Location>locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    //Hent en specifik Location efter iD
    @GetMapping("/{id}")
    public ResponseEntity<Location> getLocationById(@PathVariable int id) {
        Location location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }
    //opdater en eksisterende Location
    @PutMapping("/update/{id}")
    public ResponseEntity<Location> updateLocation(@PathVariable int id, @RequestBody Location locationDetails) {
        Location updateLocation = locationService.updateLocation(id, locationDetails);
        return ResponseEntity.ok(updateLocation);
    }
    //slet en Location
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocationById(id);
        return ResponseEntity.noContent().build();
    }

}
