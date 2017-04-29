package com.tung7.docsys.configuration;

import com.alibaba.druid.pool.DruidDataSource;
import org.hibernate.jpa.HibernatePersistenceProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.hibernate5.HibernateExceptionTranslator;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com.tung7.docsys.repositories") // basepackage for repository
@PropertySource("classpath:application.properties")
public class JpaConfiguration {
    private static final String DATABASE_DRIVER = "db.driver";
    private static final String DATABASE_URL = "db.url";
    private static final String DATABASE_USER = "db.user";
    private static final String DATABASE_PASSWORD = "db.password";

    private static final String ENTITY_PACKAGES_TO_SCAN = "jpa.entityPackagesToScan";
    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
    private static final String HIBERNATE_SHOW_SQL = "hibernate.showSql";

    @Autowired
    private Environment env;

    @Bean
    public DataSource dataSource() {
        DruidDataSource source = new DruidDataSource();
        source.setDriverClassName(env.getRequiredProperty(DATABASE_DRIVER));
        source.setUrl(env.getRequiredProperty(DATABASE_URL));
        source.setUsername(env.getRequiredProperty(DATABASE_USER));
        source.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));
        return source;
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setDataSource(dataSource());
        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
        factory.setPackagesToScan(env.getRequiredProperty(ENTITY_PACKAGES_TO_SCAN));
        factory.setJpaProperties(hibernateProperties());
        factory.afterPropertiesSet();
        return factory;
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        JpaTransactionManager manager = new JpaTransactionManager();
        manager.setEntityManagerFactory(entityManagerFactory().getObject());
        return manager;
    }

    @Bean
    public HibernateExceptionTranslator hibernateExceptionTranslator() {
        return new HibernateExceptionTranslator();
    }

    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", env.getRequiredProperty(HIBERNATE_DIALECT));
        properties.put("hibernate.show_sql", env.getRequiredProperty(HIBERNATE_SHOW_SQL));
        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));
        return properties;
    }

}
