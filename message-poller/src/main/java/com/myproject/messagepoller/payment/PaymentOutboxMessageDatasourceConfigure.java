package com.myproject.messagepoller.payment;

import com.myproject.messagepoller.consumer.AbstractOutboxMessageDatasourceConfigure;
import jakarta.annotation.Priority;
import java.util.Map;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author nguyenle
 * @since 11:53 AM Fri 12/20/2024
 */
@Configuration("paymentOutboxMessageDatasourceConfigure")
@EnableJpaRepositories(
	basePackages = "com.myproject.messagepoller.payment",
	entityManagerFactoryRef = "paymentMessageEntityManagerFactory",
	transactionManagerRef = "paymentMessageTransactionManager"
)
public class PaymentOutboxMessageDatasourceConfigure extends AbstractOutboxMessageDatasourceConfigure {

	@Value("${spring.hibernate.dialect.payment}")
	private String hibernateDialect;

	@Override
	@Priority(value = 1)
	@Bean(name = "paymentMessageDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.payment")
	protected DataSource configureDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Override
	@Priority(value = 2)
	@Bean(name = "paymentMessageEntityManagerFactory")
	protected LocalContainerEntityManagerFactoryBean configureEntityManagerFactory(
		@Qualifier("paymentMessageDataSource") DataSource dataSource,
		EntityManagerFactoryBuilder builder
	) {
		return builder
			.dataSource(dataSource)
			.packages("com.myproject.messagepoller.payment")
			.properties(configureHibernate())
			.persistenceUnit("paymentMessageDatabase")
			.build();
	}

	@Override
	@Priority(value = 3)
	@Bean(name = "paymentMessageTransactionManager")
	protected PlatformTransactionManager configureTransactionManager(
		@Qualifier("paymentMessageEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
	) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
	}

	@Override
	protected Map<String, Object> configureHibernate() {
		Map<String, Object> generalConfigs = super.configureHibernate();
		generalConfigs.put("hibernate.dialect", hibernateDialect);
		return generalConfigs;
	}
}
