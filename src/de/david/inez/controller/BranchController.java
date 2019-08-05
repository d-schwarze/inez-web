package de.david.inez.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.Branch;
import de.david.inez.models.Location;
import de.david.inez.services.BranchService;


@RestController
@RequestMapping("/branches")
public class BranchController {

	@Autowired
	private BranchService branchService;
	
	
	@GetMapping(produces = "application/json;charset=UTF-8")
	@ResponseBody
	public List<Branch> getBranches() {
		
		return branchService.getBranches();
		
	}
	
	@GetMapping(path = "/id/{id}", produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public Branch getBranch(@PathVariable("id") long id) {
		
		return branchService.getBranch(id);
		
	}
	
	@DeleteMapping("/id/{id}")
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	public void removeBranch(@PathVariable("id") long id) {
		
		branchService.removeBranch(id);
		
	}
	
	@PostMapping("/add")
	@ResponseStatus(HttpStatus.OK)
	public void addBranch(Branch branch) {
		
		branchService.addBranch(branch);
		
	}
	
	@GetMapping(path = "/closest", produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public Branch getBranch(@RequestParam("lat") double lat, @RequestParam("lng") double lng) {
		
		return branchService.getClosestBranch(new Location(lat, lng));
		
	}
}
