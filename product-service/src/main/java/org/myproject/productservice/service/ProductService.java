package org.myproject.productservice.service;

import java.util.List;
import lombok.RequiredArgsConstructor;
import org.myproject.productservice.entity.Product;
import org.myproject.productservice.repository.ProductRepository;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 10:20 AM Tue 12/17/2024
 */
@Service
@RequiredArgsConstructor
public class ProductService {

	private final ProductRepository productRepository;

	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

}
