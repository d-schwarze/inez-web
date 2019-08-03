package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Subsidiary;

public interface SubsidiaryRepository extends JpaRepository<Subsidiary, Long> {

}
