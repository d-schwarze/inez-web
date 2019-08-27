package de.david.inez.services;

import java.util.List;

import de.david.inez.models.Branch;
import de.david.inez.models.Location;

public interface BranchService {

	public List<Branch> getBranches();
	
	public Branch getClosestBranch(Location location);
	
	public void addBranch(Branch branch);
	
	public Branch getBranch(long id);
	
	public void updateBranch(Branch branch);
	
	public void removeBranch(long id);
	
}
