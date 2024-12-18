package org.myproject.orderservice.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.Date;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.myproject.orderservice.shared.AggregateType;
import org.myproject.orderservice.shared.MessageType;
import org.myproject.orderservice.utils.JsonUtils;
import org.myproject.orderservice.utils.UUIDUtils;

/**
 * @author nguyenle
 * @since 11:21 AM Wed 12/18/2024
 */
@Entity
@Table(
	name = "t_outbox_order"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderOutboxMessage {

	@Id
	private String id;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private AggregateType aggregateType;

	@Column(nullable = false)
	private String aggregateId;

	@Column(nullable = false)
	@Enumerated(EnumType.STRING)
	private MessageType type;

	@Column(columnDefinition = "TEXT")
	private String payload;

	@Column(nullable = false)
	private Date createdAt = new Date();

	private boolean sent = false;


	public static OrderOutboxMessage orderCreatedOutboxMessage(Order order) {
		OrderOutboxMessage message = new OrderOutboxMessage();
		message.setId(UUIDUtils.getRandomUUID());
		message.setAggregateType(AggregateType.ORDER);
		message.setAggregateId(order.getId());
		message.setType(MessageType.CREATED);
		message.setPayload(JsonUtils.toJson(order));
		return message;
	}
}
