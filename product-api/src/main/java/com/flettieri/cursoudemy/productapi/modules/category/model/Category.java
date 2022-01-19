package com.flettieri.cursoudemy.productapi.modules.category.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.BeanUtils;

import com.flettieri.cursoudemy.productapi.modules.category.dto.CategoryRequest;
import com.flettieri.cursoudemy.productapi.modules.category.dto.CategoryResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "CATEGORY")
public class Category {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;
	
	@Column(name = "DESCRIPTION", nullable = false)
	private String description;
	
	
	public static Category of(CategoryRequest request) {
		var category = new Category();
		BeanUtils.copyProperties(request,  category);
	
		return category;
	}
	
	public static CategoryResponse ofResponse(Category request) {
		var category = new CategoryResponse();
		BeanUtils.copyProperties(request,  category);
	
		return category;
	}
}