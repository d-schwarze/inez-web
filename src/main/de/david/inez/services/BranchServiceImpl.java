package de.david.inez.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.models.Branch;
import de.david.inez.models.Location;
import de.david.inez.repositories.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;
	
	@Override
	public List<Branch> getBranches() {
		
		return branchRepository.findAll();
		
	}

	@Override
	public Branch getClosestBranch(Location location) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addBranch(Branch branch) {
		
		branchRepository.save(branch);
		
	}

	@Override
	public Branch getBranch(long id) {
		
		return branchRepository.findById(id).get();
		
	}

	@Override
	public void updateBranch(Branch branch) {
		
		branchRepository.save(branch);
		
	}

	@Override
	public void removeBranch(long id) {
		
		branchRepository.deleteById(id);
		
	}
}
