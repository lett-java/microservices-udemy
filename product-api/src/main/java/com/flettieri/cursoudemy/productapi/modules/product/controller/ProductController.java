package com.flettieri.cursoudemy.productapi.modules.product.controller;

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
import com.flettieri.cursoudemy.productapi.modules.product.dto.ProductRequest;
import com.flettieri.cursoudemy.productapi.modules.product.dto.ProductResponse;
import com.flettieri.cursoudemy.productapi.modules.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@PostMapping
	public ProductResponse save(@RequestBody ProductRequest request) {
		return productService.save(request);
	}
	
	@GetMapping("/{id}")
	public ProductResponse findById(@PathVariable Integer id) {
		return productService.findById(id);
	}
	
	@GetMapping
	public List<ProductResponse> findAll() {
		return productService.findAll();
	}

	@GetMapping("/name/{name}")
	public List<ProductResponse> findByName(@PathVariable String name) {
		return productService.findByName(name);
	}
	
	@GetMapping("/category-id/{categoryId}")
	public List<ProductResponse> findByCategoryId(@PathVariable Integer categoryId) {
		return productService.findByCategoryId(categoryId);
	}
	
	@GetMapping("supplier-id/{supplierId}")
	public List<ProductResponse> findBySupplierId(@PathVariable Integer supplierId) {
		return productService.findBySupplierId(supplierId);
	}
	
	@PutMapping("/{id}")
	public ProductResponse update(@RequestBody ProductRequest request, @PathVariable Integer id) {
		return productService.update(request, id);
	}
	
	
	@DeleteMapping("/{id}")
	public SuccessResponse delete(@PathVariable Integer id) {
		return productService.delete(id);
	}
}
