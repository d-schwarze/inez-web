package de.david.inez.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import de.david.inez.models.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

}
