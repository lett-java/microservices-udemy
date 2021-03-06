package com.flettieri.cursoudemy.productapi.modules.supplier.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flettieri.cursoudemy.productapi.modules.supplier.model.Supplier;

public interface SupplierRepository extends JpaRepository<Supplier, Integer>{

	List<Supplier> findByNameIgnoreCaseContaining(String name);
	
}
