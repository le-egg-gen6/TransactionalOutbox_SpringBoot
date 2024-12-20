package com.myproject.messagepoller.payment;

import com.myproject.messagepoller.message.OutboxMessage;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nguyenle
 * @since 11:52 AM Fri 12/20/2024
 */
@Entity
@Table(
	name = "t_outbox_payment"
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentOutboxMessage extends OutboxMessage {

}
