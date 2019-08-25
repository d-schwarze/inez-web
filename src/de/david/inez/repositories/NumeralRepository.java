package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Numeral;

public interface NumeralRepository extends JpaRepository<Numeral, Long> {

}
