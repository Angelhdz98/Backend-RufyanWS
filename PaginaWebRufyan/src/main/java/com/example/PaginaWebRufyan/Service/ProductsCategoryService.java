package com.example.PaginaWebRufyan.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.PaginaWebRufyan.Entity.ProductsCategory;
import com.example.PaginaWebRufyan.Repository.ProductsCategoryRepository;
@Service
public class ProductsCategoryService {
	@Autowired
	ProductsCategoryRepository categoryRepository;
public List<ProductsCategory> retrieveAllCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	public Optional<ProductsCategory> retrieveCategoryById(Integer id) {
		// TODO Auto-generated method stub
		return categoryRepository.findById(id);
	}
	public ProductsCategory saveCategory(ProductsCategory productCategory) {
		// TODO Auto-generated method stub
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
