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

import de.david.inez.models.Unit;
import de.david.inez.repositories.UnitRepository;

@RestController
@RequestMapping("/units")
public class UnitController {

	@Autowired
	private UnitRepository unitRepository;
	
	
	@GetMapping
	public List<Unit> getUnits() {
		
		return unitRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Unit getUnit(@PathVariable("id") long id) {
		
		return unitRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeUnit(@PathVariable("id") long id) {
		
		unitRepository.deleteById(id);
		
	}
	
	@PostMapping("/add")
	public void addUnit(@RequestBody Unit unit) {
		
		unitRepository.save(unit);
		
	}
}
