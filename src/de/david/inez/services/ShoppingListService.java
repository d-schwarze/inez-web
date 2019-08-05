package de.david.inez.services;

import java.util.List;

import de.david.inez.exceptions.NoAccessException;
import de.david.inez.models.ShoppingList;

public interface ShoppingListService {

	public List<ShoppingList> getAllShoppingLists();
	
	public List<ShoppingList> getShoppingListsByUser(String userId);
	
	public ShoppingList getShoppingList(long id);
	
	public ShoppingList getShoppingListByUser(long shoppingListId, String userId);
	
	public void removeShoppingList(long id);
	
	public void removeShoppingListByUser(long shoppingListId, String userId) throws NoAccessException;
	
	public void addShoppingList(ShoppingList list);
	
	public void updateShoppingList(ShoppingList list);
	
	public void pinnShoppingList(long shoppingListId, String userId) throws NoAccessException;
	
	public ShoppingList getPinnedShoppingList(String userId);
	
	public boolean userHasShoppingList(long shoppingListId, String userId);
	
}
