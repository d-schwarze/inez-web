package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Location;

public interface LocationRepository extends JpaRepository<Location, Long> {

}
