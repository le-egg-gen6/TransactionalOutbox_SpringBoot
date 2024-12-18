package org.myproject.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 11:03 AM Wed 12/18/2024
 */
@Entity
@Table(
	name = "t_order"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

	@Id
	private String id;

	@Column(nullable = false)
	private String customerId;

	@Column(nullable = false)
	private String productId;

	private int quantity;

	private double price;

	@Column(nullable = false)
	private Date orderDate;

}
