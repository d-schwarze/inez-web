package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.ListedProduct;

public interface ListedProductRepository extends JpaRepository<ListedProduct, Long> {

}
