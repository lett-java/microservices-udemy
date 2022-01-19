package com.flettieri.cursoudemy.productapi.modules.product.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

import com.flettieri.cursoudemy.productapi.modules.category.model.Category;
import com.flettieri.cursoudemy.productapi.modules.product.dto.ProductRequest;
import com.flettieri.cursoudemy.productapi.modules.product.dto.ProductResponse;
import com.flettieri.cursoudemy.productapi.modules.supplier.model.Supplier;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PRODUCT")
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "NAME", nullable = false)
	private String name;
	
	@ManyToOne
	@JoinColumn(name = "FK_CATEGORY", nullable = false)
	private Category category;
	
	@ManyToOne
	@JoinColumn(name = "FK_SUPPLIER", nullable = false)
	private Supplier supplier;
	
	@Column(name = "QUANTITY_AVAILABLE", nullable = false)
	private Integer quantityAvailable;
	
	@Column(name = "CREATED_AT", nullable = false, updatable = false)
	private LocalDateTime createdAt;
	
	@PrePersist
	public void prePersist() {
		createdAt = LocalDateTime.now();
	}

	public static Product of(ProductRequest request, Category category, Supplier supplier) {
		return Product
				.builder()
				.name(request.getName())
				.quantityAvailable(request.getQuantityAvailable())
				.supplier(supplier)
				.category(category)
				.build();
	}

	public static ProductResponse ofResponse(Product product) {
		return ProductResponse
				.builder()
				.name(product.getName())
				.quantityAvailable(product.getQuantityAvailable())
				.supplier(Supplier.ofResponse(product.getSupplier()))
				.category(Category.ofResponse(product.getCategory()))
				.build();
	}


}
