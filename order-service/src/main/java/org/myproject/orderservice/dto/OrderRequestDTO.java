package org.myproject.orderservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 1:43 PM Wed 12/18/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderRequestDTO {

	private String customerId;

	private String productId;

	private int quantity;

	private double price;

}
