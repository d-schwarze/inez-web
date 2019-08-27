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
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Unit;
import de.david.inez.models.UnitSystem;
import de.david.inez.repositories.UnitSystemRepository;

@RestController
@RequestMapping("/unitsystems")
public class UnitSystemController {

	@Autowired
	private UnitSystemRepository unitSystemRepository;
	
	@GetMapping
	public List<UnitSystem> getUnits() {
		
		return unitSystemRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public UnitSystem getUnitSystem(@PathVariable("id") long id) {
		
		return unitSystemRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeUnitSystem(@PathVariable("id") long id) {
		
		unitSystemRepository.deleteById(id);
		
	}
	
	@PostMapping("/add")
	public void addUnitSystem(@RequestBody UnitSystem unitSystem) {
		
		unitSystemRepository.save(unitSystem);
		
	}
	
}
