package com.flettieri.cursoudemy.productapi.modules.supplier.service;

import static org.springframework.util.ObjectUtils.isEmpty;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flettieri.cursoudemy.productapi.config.exception.SuccessResponse;
import com.flettieri.cursoudemy.productapi.config.exception.ValidationException;
import com.flettieri.cursoudemy.productapi.modules.product.service.ProductService;
import com.flettieri.cursoudemy.productapi.modules.supplier.dto.SupplierRequest;
import com.flettieri.cursoudemy.productapi.modules.supplier.dto.SupplierResponse;
import com.flettieri.cursoudemy.productapi.modules.supplier.model.Supplier;
import com.flettieri.cursoudemy.productapi.modules.supplier.repository.SupplierRepository;

@Service
public class SupplierService {

	@Autowired
	private SupplierRepository supplierRepository;
	
	@Autowired
	private ProductService productService;
	
	public SupplierResponse save(SupplierRequest request) {
		validateSupplierNameInformed(request);
		
		return SupplierResponse.of(supplierRepository.save(Supplier.of(request)));
	}
	
	public SupplierResponse update(SupplierRequest request, Integer id) {
		validateSupplierNameInformed(request);
		var supplier = Supplier.of(request);
		supplier.setId(id);
		
		return SupplierResponse.of(supplierRepository.save(supplier));
	}
	
	public SupplierResponse findById(Integer id) {
		validateInformedId(id);
		
		return Supplier.ofResponse(
				supplierRepository
					.findById(id)
					.orElseThrow(
							() -> new ValidationException("The supplier was not found.")));
	}
	
	public Supplier findBySupplierId(Integer id) {
		validateInformedId(id);
		
		return supplierRepository
					.findById(id)
					.orElseThrow(
							() -> new ValidationException("The supplier was not found."));
	}
	
	
	public List<SupplierResponse> findByName(String name) {
		if (isEmpty(name)) {
			throw new ValidationException("The supplier name must be informed.");
		}
		
		return supplierRepository.findByNameIgnoreCaseContaining(name)
				.stream()
				.map(SupplierResponse::of)
				.collect(Collectors.toList());
	}
	
	public List<SupplierResponse> findAll() {
		return supplierRepository
				.findAll()
				.stream()
				.map(SupplierResponse::of)
				.collect(Collectors.toList());
	}
	
	
	public SuccessResponse delete(Integer id) {
		validateInformedId(id);
		
		if (productService.existsByCategoryId(id)) {
			throw new ValidationException("You cannot delete this category because it's already defined by a product.");
		}
		
		supplierRepository.deleteById(id);
		
		return SuccessResponse.create("The category was deleted");
	}
	
	private void validateInformedId(Integer id) {
		if (isEmpty(id)) {
			throw new ValidationException("The supplier ID must be informed.");
		}
	}
	

	private void validateSupplierNameInformed(SupplierRequest request) {
		if (isEmpty(request.getName())) {
			throw new ValidationException("The supplier's name was not informed.");
		}
	}
	
}
