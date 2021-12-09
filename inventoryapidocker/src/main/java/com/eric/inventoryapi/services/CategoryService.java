package com.eric.inventoryapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eric.inventoryapi.models.Category;
import com.eric.inventoryapi.models.Product;
import com.eric.inventoryapi.repositories.CategoryRepository;

@Service
public class CategoryService {
   @Autowired
	private CategoryRepository categoryRepository;   
   
   public Category addCategory(Category category) {
	 if(category.getProducts().size()>0) {  
	   for(Product product : category.getProducts()) {
		   product.setCategory(category);		   
	   }
	 }
	   return this.categoryRepository.save(category);
   }
   
   public List<Category> getAllCategories(){
   	return this.categoryRepository.findAll();
   }
   
   public Category getCategoryById(long categoryId) {
   	return this.categoryRepository.findById(categoryId).orElse(null);
   }
   
   
}
