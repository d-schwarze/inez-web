package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Entry;

public interface EntryRepository<T extends Entry> extends JpaRepository<T, Long> {

}
