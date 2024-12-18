package com.myproject.messagepoller.consumer;

/**
 * @author nguyenle
 * @since 9:49 AM Wed 12/18/2024
 */
public interface IConsumerActivity {

	void handleCreatedMessage();

	void handleConfirmedMessage();

	void handleCancelledMessage();

}
