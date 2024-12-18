package com.myproject.messagepoller.consumer;

import com.myproject.messagepoller.message.OutboxMessage;

/**
 * @author nguyenle
 * @since 9:17 AM Wed 12/18/2024
 */
public interface IOutboxMessagePoller {

	void scheduledPollingTask();

}
