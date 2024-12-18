package com.myproject.messagepoller.consumer;

import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * @author nguyenle
 * @since 9:58 AM Wed 12/18/2024
 */
public abstract class AbstractOutboxMessageDatasourceConfigure {

	@Value("${spring.jpa.properties.hibernate.hbm2ddl.auto}")
	private String hibernateHbm2DdlAuto;

	@Value("${spring.jpa.properties.hibernate.dialect}")
	private String hibernateDialect;

	@Value("${spring.jpa.properties.hibernate.show_sql}")
	private String hibernateShowSql;

	protected abstract DataSource configureDataSource();

	protected abstract LocalContainerEntityManagerFactoryBean configureEntityManagerFactory(
		DataSource dataSource,
		EntityManagerFactoryBuilder builder
	);

	protected abstract PlatformTransactionManager configureTransactionManager(
		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean
	);

	protected Map<String, Object> configureHibernate() {
		Map<String, Object> properties = new HashMap<>();
		properties.put("hibernate.hbm2ddl.auto", hibernateHbm2DdlAuto);
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShowSql);
		return properties;
	}


}
