package com.example.PaginaWebRufyan.Products.Categories;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductsCategoryService {
	@Autowired
	ProductsCategoryRepository categoryRepository;
public List<ProductsCategory> retrieveAllCategories() {
		return categoryRepository.findAll();
	}


	public Optional<ProductsCategory> retrieveCategoryById(Integer id) {
		return categoryRepository.findById(id);
	}
	
	public Optional<ProductsCategory> retrieveCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}
	
	public ProductsCategory saveCategory(ProductsCategory productCategory) {
		return categoryRepository.save(productCategory);
	}

	
	
	public ProductsCategory updateById(Integer id, ProductsCategory category) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void deleteCategoryById(Integer id) {
			
	 categoryRepository.deleteById(id);

	}


}
