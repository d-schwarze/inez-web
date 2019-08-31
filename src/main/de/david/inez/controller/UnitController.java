package de.david.inez.controller;

import java.util.List;
import java.util.Optional;

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

import de.david.inez.models.SpecialUnit;
import de.david.inez.models.Unit;
import de.david.inez.models.UnitSystem;
import de.david.inez.repositories.SpecialUnitRepository;
import de.david.inez.repositories.UnitRepository;
import de.david.inez.repositories.UnitSystemRepository;

@RestController
@RequestMapping("/units")
public class UnitController {

	@Autowired
	private UnitRepository unitRepository;
	
	@Autowired
	private SpecialUnitRepository specialUnitRepository;
	
	@Autowired
	private UnitSystemRepository unitSystemRepository;
	
	
	@GetMapping
	public List<Unit> getUnits() {
		
		return unitRepository.findAll();
		
	}
	
	@GetMapping("/special")
	public List<SpecialUnit> getSpecialUnits() {
		
		return specialUnitRepository.findAll();
		
	}
	
	@GetMapping("/{id}")
	public Unit getUnit(@PathVariable("id") long id) {
		
		return unitRepository.findById(id).get();
		
	}
	
	@GetMapping("/special/{id}")
	public SpecialUnit getSpecialUnit(@PathVariable("id") long id) {
		
		return specialUnitRepository.findById(id).get();
		
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeUnit(@PathVariable("id") long id) {
		
		unitRepository.deleteById(id);
		
	}
	
	@DeleteMapping("/special/{id}")
	@ResponseStatus(HttpStatus.OK)
	public void removeSpecialUnit(@PathVariable("id") long id) {
		
		specialUnitRepository.deleteById(id);
		
	}
	
	@PostMapping("/add")
	public void addUnit(@RequestBody Unit unit) {
		
		unitRepository.save(unit);
			
	}
	
	@PostMapping(path = "/add", params = { "unitSystem" })
	public void addUnitToUnitSystem(@RequestBody Unit unit, @RequestParam("unitSystem") Optional<Long> unitSystemId) {
		
		UnitSystem us = unitSystemRepository.findById(unitSystemId.get()).get();
			
		us.addUnit(unit);
			
		unitSystemRepository.save(us);
			
	}
	
	@PostMapping("/special/add")
	public void addSpecial(@RequestBody SpecialUnit specialUnit) {
		
		specialUnitRepository.save(specialUnit);

	}
	
	@PostMapping(path = "/special/add", params = { "unitSystem" })
	public void addSpecialUnitToUnitSystem(@RequestBody SpecialUnit specialUnit, @RequestParam("unitSystem") Optional<Long> unitSystemId) {

		UnitSystem us = unitSystemRepository.findById(unitSystemId.get()).get();
		
		us.addSpecialUnit(specialUnit);
		
		unitSystemRepository.save(us);
			
	}
}
