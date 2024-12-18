package com.myproject.messagepoller.order;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author nguyenle
 * @since 10:37 AM Wed 12/18/2024
 */
@Repository
public interface OrderOutboxMessageRepository extends JpaRepository<OrderOutboxMessage, String> {

	List<OrderOutboxMessage> findBySentIsFalse();

}
