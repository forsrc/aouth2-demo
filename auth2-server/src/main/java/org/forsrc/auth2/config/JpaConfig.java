package org.forsrc.auth2.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = "entityManagerFactory", transactionManagerRef = "transactionManager", basePackages = {
		"org.forsrc.auth2.*" })
@EntityScan({ "org.forsrc.auth2.entity" })
@Order(Ordered.HIGHEST_PRECEDENCE + 10)
public class JpaConfig {
	@Autowired
	private JpaProperties jpaProperties;

	@Autowired
	private EntityManagerFactoryBuilder builder;

	@Autowired
	private DataSource dataSource;

	@Bean(name = { "entityManagerFactory" })
	public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
		LocalContainerEntityManagerFactoryBean entityManagerFactory = this.builder.dataSource(this.dataSource)
				.packages(new String[] { "org.forsrc.auth2.entity" })
				.persistenceUnit("persistenceUnit-oauth2-server")
				.properties(this.jpaProperties.getProperties())
				.build();
		entityManagerFactory.setJpaVendorAdapter((JpaVendorAdapter) new HibernateJpaVendorAdapter());
		return entityManagerFactory;
	}

	@Bean(name = { "transactionManager" })
	public PlatformTransactionManager transactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager(entityManagerFactory().getObject());
		transactionManager.setRollbackOnCommitFailure(true);
		transactionManager.setDataSource(this.dataSource);
		return (PlatformTransactionManager) transactionManager;
	}


}
