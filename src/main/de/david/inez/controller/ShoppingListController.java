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
		
		return shoppingListService.getAllShoppingLists();
		
	}
	
	@GetMapping(produces = "application/json;charset=UTF-8", params = { "user" }) 
	@ResponseBody
	public List<ShoppingList> getAllShoppingLists(@PathParam("user") String userId) {
		
		return shoppingListService.getShoppingListsByUser(userId);
		
	}
	
	@GetMapping(path="/coming", params = { "user" }) 
	public List<ShoppingList> getComingShoppingLists(@PathParam("user") String userId) {
		
		return shoppingListService.getComingShoppingListsByUser(userId);
		
	}
	
	@GetMapping(path="/undated", params = { "user" }) 
	public List<ShoppingList> getUndatedShoppingLists(@PathParam("user") String userId) {
		
		return shoppingListService.getUndatedShoppingListsByUser(userId);
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8") 
	@ResponseBody
	public ShoppingList getShoppingList(@PathVariable("id") long id) {
		
		return shoppingListService.getShoppingList(id);
		
	}
	
	@GetMapping(path = "/{id}", produces = "application/json;charset=UTF-8", params = { "user" }) 
	@ResponseBody
	public ShoppingList getShoppingListByUser(@PathVariable("id") long id, @PathParam("user") String userId) {
		
		return shoppingListService.getShoppingListByUser(id, userId);
		
	}
	
	@PostMapping(path = {"/add", "/update"})
	public void addShoppingList(@RequestBody ShoppingList list) {
		
		shoppingListService.addShoppingList(list);
		
	}
	
	@DeleteMapping(path = "/{id}")
	public void removeShoppingList(@PathVariable("id") long id) {
		
		shoppingListService.removeShoppingList(id);
		
	}
	
	@DeleteMapping(path = "/id/{id}")
	public void removeShoppingListByUser(@PathVariable("id") long id, @PathParam("user") String userId) {
		
		shoppingListService.removeShoppingListByUser(id, userId);
		
	}

	@PostMapping(path = "/id/{id}/pinn")
	public void pinnShoppingList(@PathVariable("id") long id, @PathParam("user") String userId) {
		
		shoppingListService.pinnShoppingList(id, userId);
		
	}
	
	@GetMapping(path = "/pinned")
	public ShoppingList getPinnedShoppingList(@PathParam("user") String userId) {
		
		return shoppingListService.getPinnedShoppingList(userId);
		
	}
}
