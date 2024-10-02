package com.example.kinoxp.service;

import com.example.kinoxp.model.Location;
import com.example.kinoxp.repository.LocationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class LocationService {

    @Autowired
    private LocationRepository locationRepository;

    public Location createLocation(Location location) {
        return locationRepository.save(location);
    }
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }
    public Location getLocationById(int id) {
        Optional<Location>location = locationRepository.findById(id);
        return location.orElseThrow(()-> new NoSuchElementException("Location not found with id" + id));
    }
    public Location updateLocation(int id, Location locationDetails) {
        Location location = getLocationById(id);
        location.setAddress(locationDetails.getAddress());
        return locationRepository.save(location);
    }
    public void deleteLocationById(int id) {
        Location location = getLocationById(id);
        locationRepository.delete(location);
    }


}
