package com.myproject.messagepoller.message;

import com.myproject.messagepoller.shared.AggregateType;
import com.myproject.messagepoller.shared.MessageType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import java.util.Date;
import lombok.Getter;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 9:19 AM Wed 12/18/2024
 */
@MappedSuperclass
@Getter
@Setter
public abstract class OutboxMessage {

	@Id
	protected String id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	protected AggregateType aggregateType;

	@Column(nullable = false)
	protected String aggregateId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	protected MessageType type;

	@Column(columnDefinition = "TEXT")
	protected String payload;

	@Column(nullable = false)
	protected Date createdAt;

	private boolean sent = false;

}
