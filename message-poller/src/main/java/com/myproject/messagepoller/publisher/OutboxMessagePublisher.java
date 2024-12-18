package com.myproject.messagepoller.publisher;

import com.myproject.messagepoller.message.OutboxMessage;
import com.myproject.messagepoller.utils.JsonUtils;
import java.util.concurrent.CompletableFuture;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

/**
 * @author nguyenle
 * @since 2:51 PM Wed 12/18/2024
 */
@Service
@RequiredArgsConstructor
public class OutboxMessagePublisher {

	protected final KafkaTemplate<String, String> kafkaTemplate;

	public <T extends OutboxMessage> void publish(String topicName, T message) {
		CompletableFuture<SendResult<String, String>> future = kafkaTemplate.send(
			topicName, JsonUtils.toJson(message)
		);

		future.whenComplete((result, ex) -> {
			if (ex != null) {

			} else {

			}
		});
	}

}
