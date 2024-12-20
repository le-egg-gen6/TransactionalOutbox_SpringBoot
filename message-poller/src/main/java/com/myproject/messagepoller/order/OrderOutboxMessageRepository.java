package com.myproject.messagepoller.order;

import com.myproject.messagepoller.shared.MessageType;
import jakarta.persistence.LockModeType;
import jakarta.persistence.QueryHint;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.QueryHints;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 10:37 AM Wed 12/18/2024
 */
@Repository
@Transactional(transactionManager = "orderMessageTransactionManager")
public interface OrderOutboxMessageRepository extends JpaRepository<OrderOutboxMessage, String> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({
		@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
	})
	List<OrderOutboxMessage> findBySentIsFalseAndType(MessageType type);

}
