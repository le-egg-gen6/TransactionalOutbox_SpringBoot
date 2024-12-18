package org.myproject.orderservice.repository;

import org.myproject.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 1:40 PM Wed 12/18/2024
 */
@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

}
