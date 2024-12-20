package com.myproject.messagepoller.order;

import com.myproject.messagepoller.consumer.IOutboxMessagePoller;
import com.myproject.messagepoller.shared.MessageType;
import jakarta.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
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

	private final OrderOutboxMessageService orderOutboxMessageService;

	@Value("${outbox.topic.order}")
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
		orderOutboxMessageService.processMessage(
			mapTypeToTopicName.get(MessageType.CREATED),
			MessageType.CREATED
		);
		orderOutboxMessageService.processMessage(
			mapTypeToTopicName.get(MessageType.CONFIRMED),
			MessageType.CONFIRMED
		);
		orderOutboxMessageService.processMessage(
			mapTypeToTopicName.get(MessageType.CANCELLED),
			MessageType.CANCELLED
		);
	}

	private String getTopicName(MessageType messageType) {
		return rootTopic + "_" + messageType.name().trim().toLowerCase();
	}

}
