package com.myproject.messagepoller.order;

import com.myproject.messagepoller.consumer.IConsumerActivity;
import com.myproject.messagepoller.consumer.IOutboxMessageConsumer;
import org.springframework.stereotype.Component;

/**
 * @author nguyenle
 * @since 10:18 AM Wed 12/18/2024
 */
@Component("orderOutboxMessageConsumer")
public class OrderOutboxMessageConsumer
	implements IOutboxMessageConsumer<OrderOutboxMessage>, IConsumerActivity
{

	@Override
	public void handleCreatedMessage() {

	}

	@Override
	public void handleCancelledMessage() {

	}

	@Override
	public void handleConfirmedMessage() {

	}

	@Override
	public void consume(OrderOutboxMessage message) {

	}
}
