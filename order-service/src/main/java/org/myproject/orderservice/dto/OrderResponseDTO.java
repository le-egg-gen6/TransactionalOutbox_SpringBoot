package org.myproject.orderservice.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myproject.orderservice.entity.Order;

/**
 * @author nguyenle
 * @since 1:46 PM Wed 12/18/2024
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO {

	private String id;

	private String customerId;

	private String productId;

	private int quantity;

	private double price;

	private Date orderDate;

	public static OrderResponseDTO toOrderResponseDTO(Order order) {
		OrderResponseDTO dto = new OrderResponseDTO();
		dto.setId(order.getId());
		dto.setCustomerId(order.getCustomerId());
		dto.setProductId(order.getProductId());
		dto.setQuantity(order.getQuantity());
		dto.setPrice(order.getPrice());
		dto.setOrderDate(order.getOrderDate());
		return dto;
	}

}
