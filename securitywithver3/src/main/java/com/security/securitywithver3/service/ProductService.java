package com.security.securitywithver3.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.security.securitywithver3.dto.Product;

@Service
public class ProductService {
	
	List<Product> productList;
	
   public ProductService(List<Product> productList) {
	   
	   productList.add(new Product(101, "Mobile"));
	   productList.add(new Product(95, "laptop"));
	   productList.add(new Product(234, "home"));
	   this.productList=productList;
   }
   
   public List<Product> getAll(){
	   
	   return productList;
   }
   
   public Product getProductByid(int id) {
	   
	   Product product=null;
	   
	   for(Product pr:productList) {
		   
		   if(pr.getId()==id) {
			   product=pr;
			   break;
		   }
	   }
	   
	   return product;
   }
	

}
