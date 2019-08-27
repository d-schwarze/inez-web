package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.ProductGroup;

public interface ProductGroupRepository extends JpaRepository<ProductGroup, Long> {

}
