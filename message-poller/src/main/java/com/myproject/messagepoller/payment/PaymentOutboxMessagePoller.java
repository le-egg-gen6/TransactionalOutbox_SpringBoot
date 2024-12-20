package com.myproject.messagepoller.payment;

import com.myproject.messagepoller.consumer.IOutboxMessagePoller;
import com.myproject.messagepoller.shared.MessageType;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 * @since 12:00 PM Fri 12/20/2024
 */
@Component("paymentOutboxMessagePoller")
public class PaymentOutboxMessagePoller implements IOutboxMessagePoller {

	private final PaymentOutboxMessageService paymentOutboxMessageService;

	@Autowired
	private PaymentOutboxMessagePoller(
		@Qualifier("paymentOutboxMessageService") PaymentOutboxMessageService paymentOutboxMessageService
	) {
		this.paymentOutboxMessageService = paymentOutboxMessageService;
	}

	@Value("${outbox.topic.payment}")
	private String rootTopic;

	private Map<MessageType, String> mapTypeToTopicName;

	@PostConstruct
	private void init() {
		mapTypeToTopicName = new HashMap<>();
		for (MessageType messageType : MessageType.values()) {
			mapTypeToTopicName.put(messageType, getTopicName(messageType));
		}
	}

	@Override
	@Scheduled(fixedRate = 60 * 1000) // 1 minutes
	public void scheduledPollingTask() {
		paymentOutboxMessageService.processMessage(
			mapTypeToTopicName.get(MessageType.CREATED),
			MessageType.CREATED
		);
		paymentOutboxMessageService.processMessage(
			mapTypeToTopicName.get(MessageType.CONFIRMED),
			MessageType.CONFIRMED
		);
		paymentOutboxMessageService.processMessage(
			mapTypeToTopicName.get(MessageType.CANCELLED),
			MessageType.CANCELLED
		);
	}

	private String getTopicName(MessageType messageType) {
		return rootTopic + "_" + messageType.name().trim().toLowerCase();
	}

}

