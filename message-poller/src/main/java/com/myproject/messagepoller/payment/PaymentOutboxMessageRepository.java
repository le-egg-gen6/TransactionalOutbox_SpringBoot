package com.myproject.messagepoller.payment;

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
 * @since 11:56 AM Fri 12/20/2024
 */
@Repository
@Transactional(transactionManager = "paymentMessageTransactionManager")
public interface PaymentOutboxMessageRepository extends JpaRepository<PaymentOutboxMessage, String> {

	@Lock(LockModeType.PESSIMISTIC_READ)
	@QueryHints({
		@QueryHint(name = "javax.persistence.lock.timeout", value = "3000")
	})
	List<PaymentOutboxMessage> findBySentIsFalseAndType(MessageType type);

}
