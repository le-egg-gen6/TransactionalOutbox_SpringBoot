package com.myproject.messagepoller.order;

import com.myproject.messagepoller.consumer.IOutboxMessagePoller;
import com.myproject.messagepoller.publisher.OutboxMessagePublisher;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 * @since 10:18 AM Wed 12/18/2024
 */
@Component("orderOutboxMessagePoller")
@RequiredArgsConstructor
public class OrderOutboxMessagePoller implements IOutboxMessagePoller {

	@Value("${outbox.topic.order}")
	private String orderTopic;

	private final OutboxMessagePublisher outboxMessagePublisher;

	private final OrderOutboxMessageRepository orderOutboxMessageRepository;

	@Override
	@Scheduled(fixedRate = 60 * 1000) // 1 minutes
	public void scheduledPollingTask() {
		List<OrderOutboxMessage> unprocessedOutboxMessages = orderOutboxMessageRepository.findBySentIsFalse();

		unprocessedOutboxMessages.forEach(outboxMessage -> {
			outboxMessagePublisher.publish(orderTopic, outboxMessage);
			outboxMessage.setSent(true);
			orderOutboxMessageRepository.save(outboxMessage);
		});
	}
}
