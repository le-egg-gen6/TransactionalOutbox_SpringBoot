package com.myproject.messagepoller.order;

import com.myproject.messagepoller.message.OutboxMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 9:46 AM Wed 12/18/2024
 */
@Entity
@Table(
	name = "t_outbox_order"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderMessage extends OutboxMessage {

}
