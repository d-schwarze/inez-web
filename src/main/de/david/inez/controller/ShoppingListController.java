package de.david.inez.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import de.david.inez.models.ShoppingList;
import de.david.inez.services.ShoppingListService;

@RestController
@RequestMapping("/shoppinglists")
public class ShoppingListController {

	@Autowired
	private ShoppingListService shoppingListService;
	
	@GetMapping(produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public List<ShoppingList> getAllShoppingLists() {
		
		List<ShoppingList> shoppingLists = shoppingListService.getAllShoppingLists();
		shoppingLists.sort((s1, s2) -> s1.getDate().compareTo(s2.getDate()));
		
		return shoppingLists;
		
	}
	
	@GetMapping(produces = "application/json;charset=UTF-8", params = { "userId" }) 
	@ResponseBody
	public List<ShoppingList> getAllShoppingLists(@PathParam("userId") String userId) {
		
		List<ShoppingList> shoppingLists = shoppingListService.getShoppingListsByUser(userId);
		shoppingLists.sort((s1, s2) -> s1.getDate().compareTo(s2.getDate()));
		
		return shoppingLists;
		
	}
	
	@GetMapping(path="/coming", params = { "userId" }) 
	public List<ShoppingList> getComingShoppingLists(@PathParam("userId") String userId) {
		
		List<ShoppingList> shoppingLists = shoppingListService.getComingShoppingListsByUser(userId);
		shoppingLists.sort((s1, s2) -> s1.getDate().compareTo(s2.getDate()));
		
		return shoppingLists;
		
	}
	
	@GetMapping(path="/undated", params = { "userId" }) 
	public List<ShoppingList> getUndatedShoppingLists(@PathParam("userId") String userId) {
		
		return shoppingListService.getUndatedShoppingListsByUser(userId);
		
	}
	
	@GetMapping(path="/expired", params = { "userId" }) 
	public List<ShoppingList> getExpiredShoppingLists(@PathParam("userId") String userId) {
		
		return shoppingListService.getExpiredShoppingListsByUser(userId);
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public ShoppingList getShoppingList(@PathVariable("id") long id) {
		
		return shoppingListService.getShoppingList(id);
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8", params = { "userId" }) 
	@ResponseBody
	public ShoppingList getShoppingListByUser(@PathVariable("id") long id, @PathParam("userId") String userId) {
		
		return shoppingListService.getShoppingListByUser(id, userId);
		
	}
	
	@PostMapping(path = {"/add", "/update"}, consumes="application/json;charset=UTF-8")
	public void addShoppingList(@RequestBody ShoppingList list) {
		
		shoppingListService.addShoppingList(list);
		
		if(list.isPinned()) {
			
			shoppingListService.pinnShoppingList(list.getId(), list.getUserId());
			
		}
	}
	
	@DeleteMapping(path = "/{id}")
	public void removeShoppingList(@PathVariable("id") long id) {
		
		shoppingListService.removeShoppingList(id);
		
	}
	
	@DeleteMapping(path = "/id/{id}")
	public void removeShoppingListByUser(@PathVariable("id") long id, @PathParam("userId") String userId) {
		
		shoppingListService.removeShoppingListByUser(id, userId);
		
	}

	@PostMapping(path = "/id/{id}/pinn")
	public void pinnShoppingList(@PathVariable("id") long id, @PathParam("userId") String userId) {
		
		shoppingListService.pinnShoppingList(id, userId);
		
	}
	
	@GetMapping(path = "/pinned")
	public ShoppingList getPinnedShoppingList(@PathParam("userId") String userId) {
		
		return shoppingListService.getPinnedShoppingList(userId);
		
	}
}
