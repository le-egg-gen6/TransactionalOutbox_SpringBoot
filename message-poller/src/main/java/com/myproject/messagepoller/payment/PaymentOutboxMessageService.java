package com.myproject.messagepoller.payment;

import com.myproject.messagepoller.consumer.IOutboxMessageService;
import com.myproject.messagepoller.order.OrderOutboxMessage;
import com.myproject.messagepoller.order.OrderOutboxMessageRepository;
import com.myproject.messagepoller.publisher.OutboxMessagePublisher;
import com.myproject.messagepoller.shared.MessageType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 11:57 AM Fri 12/20/2024
 */
@Service("paymentOutboxMessageService")
@RequiredArgsConstructor
public class PaymentOutboxMessageService implements IOutboxMessageService {

	private final PaymentOutboxMessageRepository paymentOutboxMessageRepository;

	private final OutboxMessagePublisher outboxMessagePublisher;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void processMessage(String topicName, MessageType type) {
		List<PaymentOutboxMessage> unprocessedOutboxMessages = paymentOutboxMessageRepository.findBySentIsFalseAndType(type);

		unprocessedOutboxMessages.forEach(outboxMessage -> {
			outboxMessagePublisher.publish(topicName, outboxMessage);
			outboxMessage.setSent(true);
			paymentOutboxMessageRepository.save(outboxMessage);
		});
	}
}