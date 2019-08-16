package de.david.inez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Location;
import de.david.inez.repositories.LocationRepository;

@RestController
@RequestMapping("/locations")
public class LocationController {

	@Autowired
	private LocationRepository locationRepository;
	
	@GetMapping
	public List<Location> getLocations() {
		
		return locationRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Location getLocation(@PathVariable("id") long id) {
		
		return locationRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeLocation(@PathVariable("id") long id) {
		
		locationRepository.deleteById(id);
		
	}
	
	@PostMapping(path = {"/add", "/update"})
	@ResponseStatus(HttpStatus.CREATED)
	public void addLocation(@RequestBody Location location) {
		
		locationRepository.save(location);
		
	}
}
