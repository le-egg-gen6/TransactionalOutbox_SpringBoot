package org.myproject.orderservice.service;

import jakarta.transaction.Transactional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.myproject.orderservice.dto.OrderRequestDTO;
import org.myproject.orderservice.entity.Order;
import org.myproject.orderservice.entity.OrderOutboxMessage;
import org.myproject.orderservice.repository.OrderOutboxRepository;
import org.myproject.orderservice.repository.OrderRepository;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 1:42 PM Wed 12/18/2024
 */
@Service
@RequiredArgsConstructor
public class OrderService {

	private final OrderRepository orderRepository;

	private final OrderOutboxRepository orderOutboxRepository;

	@Transactional(rollbackOn = Exception.class)
	public Order createOrder(OrderRequestDTO request) {
		Order order = new Order();
		order.setId(UUID.randomUUID().toString());
		order.setCustomerId(request.getCustomerId());
		order.setProductId(request.getProductId());
		order.setQuantity(request.getQuantity());
		order.setPrice(request.getPrice());

		order = orderRepository.save(order);

		OrderOutboxMessage outboxMessage = OrderOutboxMessage.orderCreatedOutboxMessage(order);
		orderOutboxRepository.save(outboxMessage);

		return order;
	}

}
