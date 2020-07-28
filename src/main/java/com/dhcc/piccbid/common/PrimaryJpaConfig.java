package com.dhcc.piccbid.common;

import javax.persistence.EntityManager;

import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionInterceptor;

import com.dhcc.framework.common.BaseConstants;
import com.dhcc.framework.common.config.BaseJpaConfig;

@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(entityManagerFactoryRef = PrimaryJpaConfig.ENTITY_MANAGER_FACTORY_NAME, transactionManagerRef = PrimaryJpaConfig.TRANSACTION_MANAGER_NAME, basePackages = {
		"com.dhcc.framework.app.entity", "com.dhcc.framework.app.dao", "com.dhcc.piccbid.entity",
		"com.dhcc.piccbid.dao" }) // 设置Repository所在位置
@DependsOn("springContextHolder")
public class PrimaryJpaConfig extends BaseJpaConfig {

	protected static final String DB_NAME = null;
	protected static final String[] ENTITY_PACKAGES = { "com.dhcc.framework.app.entity", "com.dhcc.piccbid.entity" };
	protected static final String ASPECTJ_EXPRESSION = "execution(* com.dhcc..service..*Service*.*(..))";

	
	private static final String ENTITY_MANAGER_NAME = BaseConstants.DEFAULT_ENTITY_MANAGER_NAME;
	static final String ENTITY_MANAGER_FACTORY_NAME = BaseConstants.DEFAULT_ENTITY_MANAGER_FACTORY_NAME;
	static final String TRANSACTION_MANAGER_NAME = BaseConstants.DEFAULT_TRANSACTION_MANAGER_NAME;
	private static final String PERSISTENCE_UNIT_NAME = BaseConstants.DEFAULT_PERSISTENCE_UNIT_NAME;
	private static final String TRANSACTION_INTERCEPTOR_NAME = BaseConstants.DEFAULT_TRANSACTION_INTERCEPTOR_NAME;
	private static final String POINTCUT_ADVISOR_NAME = BaseConstants.DEFAULT_POINTCUT_ADVISOR_NAME;

	@Primary
	@Bean(name = ENTITY_MANAGER_FACTORY_NAME)
	public LocalContainerEntityManagerFactoryBean entityManagerFactory(EntityManagerFactoryBuilder builder) {
		return super.entityManagerFactory(builder, DB_NAME, PERSISTENCE_UNIT_NAME, ENTITY_PACKAGES);
	}

	@Primary
	@Bean(name = ENTITY_MANAGER_NAME)
	public EntityManager entityManager(
			@Qualifier(ENTITY_MANAGER_FACTORY_NAME) LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return super.entityManager(entityManagerFactory);
	}

	@Primary
	@Bean(name = TRANSACTION_MANAGER_NAME)
	public PlatformTransactionManager transactionManager(
			@Qualifier(ENTITY_MANAGER_FACTORY_NAME) LocalContainerEntityManagerFactoryBean entityManagerFactory) {
		return super.transactionManager(entityManagerFactory);
	}

	@Primary
	@Bean(name = TRANSACTION_INTERCEPTOR_NAME)
	@Override
	public TransactionInterceptor transactionInterceptor(
			@Qualifier(TRANSACTION_MANAGER_NAME) PlatformTransactionManager transactionManager) {
		return super.transactionInterceptor(transactionManager);
	}

	@Primary
	@Bean(name = POINTCUT_ADVISOR_NAME)
	public DefaultPointcutAdvisor pointcutAdvisor(
			@Qualifier(TRANSACTION_INTERCEPTOR_NAME) TransactionInterceptor transactionInterceptor) {
		return super.pointcutAdvisor(transactionInterceptor, ASPECTJ_EXPRESSION);
	}
}
