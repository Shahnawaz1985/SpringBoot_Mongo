package com.eric.spring.boot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.eric.spring.boot.collections.CartItem;
import com.eric.spring.boot.collections.Carts;
import com.eric.spring.boot.collections.Items;
import com.eric.spring.boot.repositories.CartsRepository;
import com.eric.spring.boot.repositories.ItemsRepository;

@Controller
public class HomeController {
	
	@Autowired
	private ItemsRepository itemRepository;

	@Autowired
	private CartsRepository cartRepository;
	
	@ModelAttribute("items")
	  public Collection<Items> populateItem() {
		Collection<Items> resultItems = (Collection<Items>) this.itemRepository.findAll();
	    return resultItems;
	  }
	
	@ModelAttribute("cart")
	  public Collection<Carts> populateCart() {
		Collection<Carts> resultCarts = (Collection<Carts>) this.cartRepository.findAll();
		if(null == resultCarts) {
			Carts tempCart = new Carts("My Cart");
			CartItem cartItem = new CartItem();
			cartItem.setItem(new Items("Roller pen", 43.98));
			cartItem.increment();
			if(null != tempCart.getCartItems()) {
				tempCart.getCartItems().add(cartItem);
			}else {
				List<CartItem> cartList = new ArrayList<CartItem>();
				cartList.add(cartItem);
				tempCart.setCartItems(cartList);
			}
			resultCarts = new ArrayList<Carts>();
			resultCarts.add(tempCart);
			
		}
		
	    return resultCarts;
	  }
	
	@GetMapping(value = "/")
	public String getHome() {
		return "item";
	}

}
