package com.flettieri.cursoudemy.productapi.modules.category.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flettieri.cursoudemy.productapi.modules.category.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

	List<Category> findByDescriptionIgnoreCaseContaining(String description);

	
}
