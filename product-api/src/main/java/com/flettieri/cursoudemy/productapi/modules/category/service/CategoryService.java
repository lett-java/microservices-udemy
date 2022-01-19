package com.flettieri.cursoudemy.productapi.modules.category.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flettieri.cursoudemy.productapi.config.exception.SuccessResponse;
import com.flettieri.cursoudemy.productapi.config.exception.ValidationException;
import com.flettieri.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import com.flettieri.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import com.flettieri.cursoudemy.productapi.modules.category.model.Category;
import com.flettieri.cursoudemy.productapi.modules.category.repository.CategoryRepository;
import com.flettieri.cursoudemy.productapi.modules.product.service.ProductService;

@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private ProductService productService;
	
	public CategoryResponse save(CategoryRequest request) {
		validateCategoryDescriptionInformed(request);
		
		return Category.ofResponse((Category) categoryRepository.save(Category.of(request)));
	}
	
	public CategoryResponse update(CategoryRequest request, Integer id) {
		validateCategoryDescriptionInformed(request);
		var category = Category.of(request);
		category.setId(id);
		
		return Category.ofResponse((Category) categoryRepository.save(category));
	}

	
	public Category findById(Integer id) {
		validateInformedId(id);
		
		return categoryRepository.findById(id)
				.orElseThrow(() -> new ValidationException("The category was not found."));
	}
	
	public List<CategoryResponse> findByDescription(String description) {
		if (isEmpty(description)) {
			throw new ValidationException("The category description must be informed");
		}
		
		return categoryRepository
				.findByDescriptionIgnoreCaseContaining(description)
				.stream()
				.map(CategoryResponse::of)
				.collect(Collectors.toList()); 
	}
	
	public List<CategoryResponse> findAll() {
		return categoryRepository
				.findAll()
				.stream()
				.map(CategoryResponse::of)
				.collect(Collectors.toList()); 
	}
	
	public SuccessResponse delete(Integer id) {
		validateInformedId(id);
		
		if (productService.existsByCategoryId(id)) {
			throw new ValidationException("You cannot delete this category because it's already defined by a product.");
		}
		
		categoryRepository.deleteById(id);
		
		return SuccessResponse.create("The category was deleted");
	}
	
	private void validateInformedId(Integer id) {
		if (isEmpty(id)) {
			throw new ValidationException("The category ID must be informed.");
		}
	}
	
	private void validateCategoryDescriptionInformed(CategoryRequest request) {
		if (isEmpty(request.getDescription())) {
			throw new ValidationException("The category description was not informed.");
		}
	}
}
