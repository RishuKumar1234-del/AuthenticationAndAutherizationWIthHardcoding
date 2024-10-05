package com.security.securitywithver3.controller;

import java.util.List;

import javax.management.relation.RoleStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.security.securitywithver3.dto.Product;
import com.security.securitywithver3.service.ProductService;

@RestController
@RequestMapping("/products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/welcome")
	public String welcome() {
		
		return "hello welcome,this is not secure end point";
	}
	
	@GetMapping("/getAll")
	@PreAuthorize("hasAuthority('ROLE_ADMIN')")
	public List<Product> findAllProduct(){
		
		return productService.getAll();
		
	}
	
	@GetMapping("/getAll/{id}")
	@PreAuthorize("hasAuthority('ROLE_USER')")
	public Product getProductByid(@PathVariable int id) {
		
		return productService.getProductByid(id);
	}
	
	

}
