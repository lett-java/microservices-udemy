package com.flettieri.cursoudemy.productapi;

import java.util.HashMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class StatusController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StatusController.class);
	
	@GetMapping("/status")
	public ResponseEntity<HashMap<String, Object>> getApiStatus() {
		LOGGER.info("StatusController.class - getApiStatus() START" );
		var response = new HashMap<String, Object>();
		
		response.put("service", "Product-API");
		response.put("status", "up");
		response.put("httpStatus", HttpStatus.OK.value());
		
		LOGGER.info("StatusController.class - getApiStatus() FINISH" );

		return ResponseEntity.ok(response);
	}
}
