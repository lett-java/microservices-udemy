package com.flettieri.cursoudemy.productapi.modules.product.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flettieri.cursoudemy.productapi.config.exception.SuccessResponse;
import com.flettieri.cursoudemy.productapi.config.exception.ValidationException;
import com.flettieri.cursoudemy.productapi.modules.category.service.CategoryService;
import com.flettieri.cursoudemy.productapi.modules.product.dto.ProductRequest;
import com.flettieri.cursoudemy.productapi.modules.product.dto.ProductResponse;
import com.flettieri.cursoudemy.productapi.modules.product.model.Product;
import com.flettieri.cursoudemy.productapi.modules.product.repository.ProductRepository;
import com.flettieri.cursoudemy.productapi.modules.supplier.service.SupplierService;

@Service
public class ProductService {
	
	private static final int ZERO = 0;

	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SupplierService supplierService;
	
	@Autowired
	private CategoryService categoryService;
	
	public ProductResponse save(ProductRequest request) {
		validateProductDataInformed(request);
		validateCategoryAndSupplierIdInformed(request);
		
		var category = categoryService.findById(request.getCategoryId());
		var supplier = supplierService.findBySupplierId(request.getSupplierId());
		
		return ProductResponse.of(productRepository.save(Product.of(request, category, supplier)));
	}
	
	public ProductResponse update(ProductRequest request, Integer id) {
		validateProductDataInformed(request);
		validateCategoryAndSupplierIdInformed(request);
		
		var category = categoryService.findById(request.getCategoryId());
		var supplier = supplierService.findBySupplierId(request.getSupplierId());
		var product = Product.of(request, category, supplier);
		product.setId(id);
		
		return ProductResponse.of(productRepository.save(product));
	}
	
	public List<ProductResponse> findAll() {
		return productRepository
				.findAll()
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());
	}
	
	public List<ProductResponse> findByName(String name) {
		if (isEmpty(name)) {
			throw new ValidationException("the product NAME was not informed.");
		}

		return productRepository.findByNameIgnoreCaseContaining(name)
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList()); 
	}
	
	public ProductResponse findById(Integer id) {
		if (isEmpty(id)) {
			throw new ValidationException("The product ID was not informed.");
		}
		
		return Product.ofResponse(productRepository
					.findById(id)
					.orElseThrow(
							() -> new ValidationException("The product was not found.")));
	}
	
	public List<ProductResponse> findBySupplierId(Integer supplierId) {
		if (isEmpty(supplierId)) {
			throw new ValidationException("The supplier ID was not informed.");
		}
		
		return productRepository
					.findBySupplierId(supplierId)
						.stream()
						.map(ProductResponse::of)
						.collect(Collectors.toList());
	}

	public List<ProductResponse> findByCategoryId(Integer categoryId) {
		if (isEmpty(categoryId)) {
			throw new ValidationException("The category ID was not informed.");
		}
		
		return productRepository
				.findBySupplierId(categoryId)
				.stream()
				.map(ProductResponse::of)
				.collect(Collectors.toList());
	}
	
	public Boolean existsByCategoryId(Integer id) {
		return productRepository.existsByCategoryId(id);
	}
	
	public Boolean existsBySupplierId(Integer id) {
		return productRepository.existsBySupplierId(id);
	}

	private void validateProductDataInformed(ProductRequest request) {
		if (isEmpty(request.getName())) {
			throw new ValidationException("The product's name was not informed.");
		}
		
		if (isEmpty(request.getQuantityAvailable())) {
			throw new ValidationException("The product's quantity was not informed.");
		}

		if (request.getQuantityAvailable() <= ZERO) {
			throw new ValidationException("The quantity should not be less or equal to zero.");
		}
		
		
	}
	
	private void validateCategoryAndSupplierIdInformed(ProductRequest request) {
		if (isEmpty(request.getCategoryId())) {
			throw new ValidationException("The category ID was not informed.");
		}
		
		if (isEmpty(request.getSupplierId())) {
			throw new ValidationException("The supplier ID was not informed.");
		}
	}

	public SuccessResponse delete(Integer id) {
		validateInformedId(id);
		productRepository.deleteById(id);
	
		return SuccessResponse.create("The product was deleted.");
	}

	private void validateInformedId(Integer id) {
		if(isEmpty(id)) {
			throw new ValidationException("The product ID must be informed.");
		}
		
	}
	
	

}
