package com.myproject.messagepoller.order;

import com.myproject.messagepoller.consumer.IOutboxMessageService;
import com.myproject.messagepoller.publisher.OutboxMessagePublisher;
import com.myproject.messagepoller.shared.MessageType;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author nguyenle
 * @since 11:39 AM Fri 12/20/2024
 */
@Service("orderOutboxMessageService")
@RequiredArgsConstructor
public class OrderOutboxMessageService implements IOutboxMessageService {

	private final OrderOutboxMessageRepository orderOutboxMessageRepository;

	private final OutboxMessagePublisher outboxMessagePublisher;

	@Override
	@Transactional(rollbackFor = Exception.class)
	public void processMessage(String topicName, MessageType type) {
		List<OrderOutboxMessage> unprocessedOutboxMessages = orderOutboxMessageRepository.findBySentIsFalseAndType(type);

		unprocessedOutboxMessages.forEach(outboxMessage -> {
			outboxMessagePublisher.publish(topicName, outboxMessage);
			outboxMessage.setSent(true);
			orderOutboxMessageRepository.save(outboxMessage);
		});
	}
}
