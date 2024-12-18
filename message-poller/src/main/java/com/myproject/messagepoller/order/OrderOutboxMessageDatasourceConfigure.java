package com.myproject.messagepoller.order;

import com.myproject.messagepoller.consumer.AbstractOutboxMessageDatasourceConfigure;
import jakarta.annotation.Priority;
import java.util.Objects;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Qualifier;
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
 * @since 4:41 PM Wed 12/18/2024
 */
@Configuration
@EnableJpaRepositories(
	basePackages = "com.myproject.messagepoller.order",
	entityManagerFactoryRef = "orderMessageEntityManagerFactory",
	transactionManagerRef = "orderMessageTransactionManager"
)
public class OrderOutboxMessageDatasourceConfigure extends AbstractOutboxMessageDatasourceConfigure {

	@Override
	@Priority(value = 1)
	@Bean(name = "orderMessageDataSource")
	@ConfigurationProperties(prefix = "spring.datasource.order")
	protected DataSource configureDataSource() {
		return DataSourceBuilder.create().build();
	}

	@Override
	@Priority(value = 2)
	@Bean(name = "orderMessageEntityManagerFactory")
	protected LocalContainerEntityManagerFactoryBean configureEntityManagerFactory(
		@Qualifier("orderMessageDataSource") DataSource dataSource,
		EntityManagerFactoryBuilder builder
	) {
		return builder
			.dataSource(dataSource)
			.packages("com.myproject.messagepoller.order")
			.properties(configureHibernate())
			.persistenceUnit("orderMessageDatabase")
			.build();
	}

	@Override
	@Priority(value = 3)
	@Bean(name = "orderMessageTransactionManager")
	protected PlatformTransactionManager configureTransactionManager(
		@Qualifier("orderMessageEntityManagerFactory") LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
	) {
		return new JpaTransactionManager(Objects.requireNonNull(entityManagerFactoryBean.getObject()));
	}
}
