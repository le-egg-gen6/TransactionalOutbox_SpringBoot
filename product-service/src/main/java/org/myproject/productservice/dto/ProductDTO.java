package org.myproject.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myproject.productservice.entity.Product;

/**
 * @author nguyenle
 * @since 10:22 AM Tue 12/17/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDTO {

	private Long id;

	private String name;

	private int quantity;

	private double price;

	public ProductDTO(Product product) {
		this.id = product.getId();
		this.name = product.getName();
		this.quantity = product.getQuantity();
		this.price = product.getPrice();
	}

}
