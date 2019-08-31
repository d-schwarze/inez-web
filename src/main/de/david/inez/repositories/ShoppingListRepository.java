package de.david.inez.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import de.david.inez.models.ShoppingList;

public interface ShoppingListRepository extends JpaRepository<ShoppingList, Long> {

	@Transactional
    @Modifying
	@Query("UPDATE #{#entityName} SET pinned = false WHERE userId = :userId")
	void resetPinnedShoppingList(String userId);

	@Transactional
    @Modifying
	@Query("UPDATE #{#entityName} SET pinned = true WHERE userId = :userId AND id = :shoppingListId")
	void setPinnedShoppingList(long shoppingListId, String userId);

	@Query("SELECT sl FROM #{#entityName} as sl WHERE sl.userId = :userId AND sl.pinned = true")
	Optional<ShoppingList> getPinnedShoppingList(String userId);

}
