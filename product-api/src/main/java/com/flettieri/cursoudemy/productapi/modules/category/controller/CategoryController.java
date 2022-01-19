package com.flettieri.cursoudemy.productapi.modules.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.flettieri.cursoudemy.productapi.config.exception.SuccessResponse;
import com.flettieri.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import com.flettieri.cursoudemy.productapi.modules.category.dto.CategoryResponse;
import com.flettieri.cursoudemy.productapi.modules.category.model.Category;
import com.flettieri.cursoudemy.productapi.modules.category.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryService;
	
	@PostMapping
	public CategoryResponse save(@RequestBody CategoryRequest request) {
		return categoryService.save(request);
	}
	
	@GetMapping("/{id}")
	public CategoryResponse findById(@PathVariable Integer id) {
		return Category.ofResponse(categoryService.findById(id));
	}
	
	@GetMapping("/description/{description}")
	public List<CategoryResponse> findByDescription(@PathVariable String description) {
		return categoryService.findByDescription(description);
	}
	
	@GetMapping
	public List<CategoryResponse> findAll() {
		return categoryService.findAll();
	}
	
	@PutMapping("/{id}")
	public CategoryResponse update(@RequestBody CategoryRequest request, @PathVariable Integer id) {
		return categoryService.update(request, id);
	}
	
	@DeleteMapping("/{id}")
	public SuccessResponse delete(@PathVariable Integer id) {
		return categoryService.delete(id);
	}
	
}
