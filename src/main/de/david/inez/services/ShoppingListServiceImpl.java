package de.david.inez.services;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import de.david.inez.exceptions.NoAccessException;
import de.david.inez.models.ShoppingList;
import de.david.inez.repositories.ShoppingListRepository;

@Service
public class ShoppingListServiceImpl implements ShoppingListService {

	@Autowired
	private ShoppingListRepository shoppingListRepo;
	
	@Override
	public List<ShoppingList> getAllShoppingLists() {
		
		return shoppingListRepo.findAll();
		
	}

	@Override
	public List<ShoppingList> getShoppingListsByUser(String userId) {
		
		return shoppingListRepo.findAll()
			   				   .stream()
		   				   	   .filter(sl -> sl.getUserId().equals(userId))
		   				   	   .collect(Collectors.toList());
		
	}
	
	@Override
	public List<ShoppingList> getComingShoppingListsByUser(String userId) {
		
		return shoppingListRepo.findAll()
							   .stream()
							   .filter(sl -> sl.getUserId().equals(userId) && sl.getDate() != null && sl.getDate().isAfter(LocalDate.now()))
							   .collect(Collectors.toList());
		
	}

	@Override
	public List<ShoppingList> getUndatedShoppingListsByUser(String userId) {
		
		return shoppingListRepo.findAll()
							   .stream()
							   .filter(sl -> sl.getUserId().equals(userId) && sl.getDate() == null)
							   .collect(Collectors.toList());
		
	}
	
	@Override
	public List<ShoppingList> getExpiredShoppingListsByUser(String userId) {
		
		return shoppingListRepo.findAll()
							   .stream()
							   .filter(sl -> sl.getUserId().equals(userId) && sl.getDate() != null && sl.getDate().isBefore(LocalDate.now()))
							   .collect(Collectors.toList());
	}

	@Override
	public ShoppingList getShoppingList(long id) {
		
		return shoppingListRepo.findById(id).get();
		
	}

	@Override
	public ShoppingList getShoppingListByUser(long shoppingListId, String userId) {
		
		return shoppingListRepo.findById(shoppingListId).filter(p -> p.getUserId().equals(userId)).get();
		
	}

	@Override
	public void removeShoppingList(long id) {
		
		shoppingListRepo.deleteById(id);
		
	}

	@Override
	public void removeShoppingListByUser(long shoppingListId, String userId) throws NoAccessException {
		
		ShoppingList list = this.getShoppingList(shoppingListId);
		
		if(list.getUserId().equals(userId)) 
			shoppingListRepo.deleteById(shoppingListId);
		else
			throw new NoAccessException("User has no shopping list with ID: " + shoppingListId);
		
	}

	@Override
	public void addShoppingList(ShoppingList list) {
		
		shoppingListRepo.save(list);
		
		if(list.isPinned())
			this.pinnShoppingList(list.getId(), list.getUserId());
		
	}

	@Override
	public void updateShoppingList(ShoppingList list) {
		
		shoppingListRepo.save(list);
		
		if(list.isPinned())
			this.pinnShoppingList(list.getId(), list.getUserId());
		
	}

	@Override
	public void pinnShoppingList(long shoppingListId, String userId) throws NoAccessException {
		
		if(this.userHasShoppingList(shoppingListId, userId)) {
			
			shoppingListRepo.resetPinnedShoppingList(userId);
			
			shoppingListRepo.setPinnedShoppingList(shoppingListId, userId);
			
		} else {
			
			throw new NoAccessException("User has no shopping list with ID: " + shoppingListId);
			
		}
	}

	public boolean userHasShoppingList(long shoppingListId, String userId) {
		
		return shoppingListRepo.findById(shoppingListId).filter(p -> p.getUserId().equals(userId)).isPresent();
		
	}

	@Override
	public ShoppingList getPinnedShoppingList(String userId) {
		
		return shoppingListRepo.getPinnedShoppingList(userId).orElse(null);
		
	}
}
