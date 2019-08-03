package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import de.david.inez.models.Product;

@NoRepositoryBean
public interface ProductRepository<T extends Product> extends JpaRepository<T, Long> {

}
