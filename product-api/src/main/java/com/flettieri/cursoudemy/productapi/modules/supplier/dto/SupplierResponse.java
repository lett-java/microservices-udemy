package com.flettieri.cursoudemy.productapi.modules.supplier.dto;

import org.springframework.beans.BeanUtils;

import com.flettieri.cursoudemy.productapi.modules.supplier.model.Supplier;

import lombok.Data;

@Data
public class SupplierResponse {

	private Integer id;
	private String name;
	
	public static SupplierResponse of(Supplier supplier) {
		var response = new SupplierResponse();
		BeanUtils.copyProperties(supplier, response);
		
		return response;
	}
	
}
