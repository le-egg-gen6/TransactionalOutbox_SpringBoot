package org.myproject.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
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

}
