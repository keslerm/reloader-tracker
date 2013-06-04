package com.dasbiersec.reloader.config;

import com.dasbiersec.reloader.dao.ReportingDAO;
import org.hibernate.ejb.HibernatePersistence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;
import java.util.Properties;

@Configuration
@ComponentScan({"com.dasbiersec.reloader.service", "com.dasbiersec.reloader.exception", "com.dasbiersec.reloader.helpers", "com.dasbiersec.reloader.controller"})
@EnableJpaRepositories("com.dasbiersec.reloader.repos")
@EnableTransactionManagement
@Import(ConfigProperties.class)
@ImportResource("classpath:security-config.xml")
public class ApplicationConfig
{
	@Value("${jdbc.driver}") private String jdbcDriver;
	@Value("${jdbc.url}") private String jdbcURL;
	@Value("${jdbc.user}") private String jdbcUser;
	@Value("${jdbc.password}") private String jdbcPassword;

	@Value("${hibernate.dialect}") private String hibernateDialect;
	@Value("${hibernate.show_sql}") private String hibernateShowSQL;
	@Value("${hibernate.hbm2ddl}") private String hibernateHBM2DDLAuto;

	@Bean
	public ReportingDAO reportingDAO()
	{
		return new ReportingDAO(dataSource());
	}

	@Bean
	public DataSource dataSource()
	{
		DriverManagerDataSource ds = new DriverManagerDataSource();
		ds.setDriverClassName(jdbcDriver);
		ds.setUrl(jdbcURL);
		ds.setUsername(jdbcUser);
		ds.setPassword(jdbcPassword);
		return ds;
	}

	@Bean
	public PlatformTransactionManager transactionManager()
	{
		JpaTransactionManager txManager = new JpaTransactionManager();
		txManager.setEntityManagerFactory(entityManagerFactory());
		return txManager;
	}

	@Bean
	public HibernateExceptionTranslator hibernateExceptionTranslator(){
		return new HibernateExceptionTranslator();
	}

	@Bean
	public Properties getHibernateProperties()
	{
		Properties properties = new Properties();
		properties.put("hibernate.dialect", hibernateDialect);
		properties.put("hibernate.show_sql", hibernateShowSQL);
		properties.put("hibernate.hbm2ddl.auto", hibernateHBM2DDLAuto);
		return properties;
	}

	@Bean
	public EntityManagerFactory entityManagerFactory()
	{
		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		vendorAdapter.setDatabase(Database.POSTGRESQL);
		vendorAdapter.setGenerateDdl(true);

		LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
		entityManagerFactoryBean.setDataSource(dataSource());
		entityManagerFactoryBean.setJpaVendorAdapter(vendorAdapter);

		entityManagerFactoryBean.setPackagesToScan(new String[] { "com.dasbiersec.reloader.model" });
		entityManagerFactoryBean.setPersistenceProvider(new HibernatePersistence());
		entityManagerFactoryBean.afterPropertiesSet();
		return entityManagerFactoryBean.getObject();
	}

}
