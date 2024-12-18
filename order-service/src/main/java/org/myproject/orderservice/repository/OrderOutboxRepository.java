package org.myproject.orderservice.repository;

import org.myproject.orderservice.entity.OrderOutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 1:41 PM Wed 12/18/2024
 */
@Repository
public interface OrderOutboxRepository extends JpaRepository<OrderOutboxMessage, String> {

}
