package org.myproject.productservice.controller;

import lombok.RequiredArgsConstructor;
import org.myproject.productservice.service.ProductService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author nguyenle
 * @since 1:39 PM Tue 12/17/2024
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

	private final ProductService productService;

	@GetMapping("/all")
	public ResponseEntity<?> getAllProducts() {
		return ResponseEntity.ok(productService.getAllProducts());
	}

}
