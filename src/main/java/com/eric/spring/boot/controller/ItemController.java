package com.eric.spring.boot.controller;

import java.util.Collection;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;

import com.eric.spring.boot.collections.Carts;
import com.eric.spring.boot.collections.Items;
import com.eric.spring.boot.repositories.CartsRepository;
import com.eric.spring.boot.repositories.ItemsRepository;



@RestController
public class ItemController {
	
	private static final String PATH = "/error";
	
	private final ErrorAttributes errorAttributes;
	
	@Autowired
	private ItemsRepository itemRepository;

	@Autowired
	private CartsRepository cartRepository;
	
	@Autowired
	public ItemController(ErrorAttributes errorAttributes) {
	    Assert.notNull(errorAttributes, "ErrorAttributes must not be null");
	    this.errorAttributes = errorAttributes;
	  }
	
	/**
	@GetMapping(value = "/")
	public ResponseEntity<Collection<Items>> items() {
		System.out.println("********************Finding Items*******************************");
		Collection<Items> resultItems = (Collection<Items>) this.itemRepository.findAll();
		System.out.println("Captured Items : "+resultItems);
		System.out.println("********************Items*******************************");
		Collection<Carts> resultCarts = (Collection<Carts>) this.cartRepository.findAll();
		return ResponseEntity.ok().body(resultItems);
	}*/
	
	@GetMapping(value = "/items", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<Items>> items() {
		System.out.println("********************Finding Items*******************************");
		Collection<Items> resultItems = (Collection<Items>) this.itemRepository.findAll();
		System.out.println("Captured Items : "+resultItems);
		System.out.println("********************Items*******************************");
		Collection<Carts> resultCarts = (Collection<Carts>) this.cartRepository.findAll();
		return ResponseEntity.ok().body(resultItems);
	}
	
	 public String getErrorPath() {
	    return PATH;
	  }

	 @GetMapping(value = "/error")
	  public Map<String, Object> error(HttpServletRequest aRequest){
	     Map<String, Object> body = getErrorAttributes(aRequest,getTraceParameter(aRequest));
	     String trace = (String) body.get("trace");
	     if(trace != null){
	       String[] lines = trace.split("\n\t");
	       body.put("trace", lines);
	     }
	     return body;
	  }

	  private boolean getTraceParameter(HttpServletRequest request) {
	    String parameter = request.getParameter("trace");
	    if (parameter == null) {
	        return false;
	    }
	    return !"false".equals(parameter.toLowerCase());
	  }

	  private Map<String, Object> getErrorAttributes(HttpServletRequest aRequest, boolean includeStackTrace) {
		WebRequest requestAttributes =  new ServletWebRequest(aRequest);
	    return errorAttributes.getErrorAttributes(requestAttributes, ErrorAttributeOptions.defaults());//getErrorAttributes(requestAttributes, includeStackTrace);
	  }

}
