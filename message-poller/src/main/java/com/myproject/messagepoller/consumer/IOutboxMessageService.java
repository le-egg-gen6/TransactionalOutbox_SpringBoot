package com.myproject.messagepoller.consumer;

import com.myproject.messagepoller.shared.MessageType;

/**
 * @author nguyenle
 * @since 11:39 AM Fri 12/20/2024
 */
public interface IOutboxMessageService {

	void processMessage(String topicName, MessageType type);

}
