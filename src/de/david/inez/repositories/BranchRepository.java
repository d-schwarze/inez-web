package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Branch;

public interface BranchRepository extends JpaRepository<Branch, Long> {

}
