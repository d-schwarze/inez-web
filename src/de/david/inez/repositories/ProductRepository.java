package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
