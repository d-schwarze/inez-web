package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Name;

public interface NameRepository extends JpaRepository<Name, Long> {

}
