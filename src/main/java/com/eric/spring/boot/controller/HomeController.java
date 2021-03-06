package com.eric.spring.boot.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
	
	@GetMapping(value = "/")
	public String getHome() {
		return "item";
	}
	
	@ModelAttribute("items")
	  public Collection<Items> populateItem() {
		Collection<Items> resultItems = (Collection<Items>) this.itemRepository.findAll();
	    return resultItems;
	  }
	
	@ModelAttribute("cart")
	  public Collection<Carts> populateCart() {
		Collection<Carts> resultCarts = (Collection<Carts>) this.cartRepository.findAll();
		List<CartItem> cartList = null;
		Carts tempCart = null;
		if(null == resultCarts) {
			tempCart = new Carts("My Cart");
			CartItem cartItem = new CartItem();
			//cartItem.setItem(new Items("Roller pen", 43.98));
			//cartItem.increment();
			cartList = new ArrayList<CartItem>();
			cartList.add(cartItem);
			tempCart.setCartItems(cartList);
			resultCarts = new ArrayList<Carts>();
			resultCarts.add(tempCart);
			//this.cartRepository.saveAll(resultCarts);
		}else {
			cartList = new ArrayList<CartItem>();
			Carts tempCrt = null;
			for(Carts crt : resultCarts) {
				if(crt.getId().equals("My Cart")) {
					tempCrt = crt;
					break;
				}
			}
			if(null != tempCrt) {
				tempCrt.setCartItems(cartList);
				resultCarts.add(tempCrt);
				//this.cartRepository.saveAll(resultCarts);
			}
		}
		
	    return resultCarts;
	  }
	
	@ModelAttribute("myCartItems")
	  public Collection<CartItem> populateCartItems() {
		Collection<Carts> resultCarts = (Collection<Carts>) this.cartRepository.findAll();
		List<CartItem> cartList = null;
		Carts tempCart = null;
		if(null == resultCarts) {
			System.out.println("Initiating My Cart creation!");
			tempCart = new Carts("My Cart");
			CartItem cartItem = new CartItem();
			//cartItem.setItem(new Items("Roller pen", 43.98));
			//cartItem.increment();
			cartList = new ArrayList<CartItem>();
			cartList.add(cartItem);
			tempCart.setCartItems(cartList);
			resultCarts = new ArrayList<Carts>();
			resultCarts.add(tempCart);
			//this.cartRepository.saveAll(resultCarts);
		}else {
			System.out.println("Updating My Cart!");
			for(Carts cts : resultCarts) {
				if(cts.getId().equals("My Cart")) {
					tempCart = cts;
					break;
				}
			}
			if(null != tempCart) {
				cartList = tempCart.getCartItems();
			}
		}
		
	    return cartList;
	  }
	
	@PostMapping("/add/{id}")
	public String addToCart(@PathVariable String id) {
		
		Collection<Carts> carts = (Collection<Carts>) this.cartRepository.findAll(); 
		Carts cart = null;
		Items itm = null;
		CartItem cartItem = null;
		
		for(Carts cts : carts) {
			if(cts.getId().equals("My Cart")) {
				cart = cts;
				break;
			}
		}
		
		System.out.println("Identified cart : "+cart);
		
		if(null != cart)  {
			List<CartItem> cartItmList = null;
			cartItem = new CartItem();
			Collection<Items> items = (Collection<Items>) this.itemRepository.findAll();
			System.out.println("Items : "+items);
			for(Items itms : items) {
				if(itms.getId().equals(id)) {
					System.out.println("Item found for null != cart : "+itms.getId());
					itm = itms;
					cartItem.setItem(itms);
					cartItem.increment();
					break;
				}
			}
			if(null != cart.getCartItems()) {
				if(!cart.getCartItems().contains(cartItem)) {
					cart.getCartItems().add(cartItem);
				}else {
					cart.getCartItems().remove(cartItem);
					cartItem.increment();
					cart.getCartItems().add(cartItem);
				}
			}else {
				cartItmList = new ArrayList<CartItem>();
				cartItmList.add(cartItem);
				cart.setCartItems(cartItmList);
			}
			this.cartRepository.save(cart);
			System.out.println("Updated cartItem : "+cartItem);
			System.out.println("Updated cart : "+cart);
			System.out.println("Updated cartItemList : "+cart.getCartItems());
		}else {
				cart = new Carts("My Cart");
				List<CartItem> crtItms = new ArrayList<CartItem>();
				cart.setCartItems(crtItms);
				cartItem = new CartItem();
				crtItms.add(cartItem);
				Collection<Items> items = (Collection<Items>) this.itemRepository.findAll();
				for(Items itms : items) {
					if(itms.getId().equals(id)) {
						System.out.println("Item found : "+itms.getId());
						cartItem.setItem(itms);
						cartItem.increment();
						break;
					}
				}
				System.out.println("Updated cartItem : "+cartItem);
				System.out.println("Updated cart : "+cart);
				System.out.println("Updated cartItemList : "+crtItms);
				this.cartRepository.save(cart);
			}
		
		return "redirect:/";
	}

}
