package com.tung7.docsys.configuration;

import com.tung7.docsys.entity.DocUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * TODO Fill The Description!
 *
 * @author Tung
 * @version 1.0
 * @date 2017/4/29.
 * @update
 */
@Configuration
//@EnableTransactionManagement
@EnableJpaAuditing(auditorAwareRef = "userAuditorAware")
//@EnableJpaRepositories(basePackages = "com.tung7.docsys.repositories") // base package for repository
//@PropertySource("classpath:application.properties")
public class JpaConfiguration {

    @Bean
    public AuditorAware<DocUser> userAuditorAware() {
        return () ->{
            Subject subject = SecurityUtils.getSubject();
            Object object = subject.getPrincipal();
            return (DocUser) object;
        };
    }

//    private static final String DATABASE_DRIVER = "db.driver";
//    private static final String DATABASE_URL = "db.url";
//    private static final String DATABASE_USER = "db.user";
//    private static final String DATABASE_PASSWORD = "db.password";
//
//    private static final String ENTITY_PACKAGES_TO_SCAN = "jpa.entityPackagesToScan";
//    private static final String HIBERNATE_DIALECT = "hibernate.dialect";
//    private static final String HIBERNATE_HBM2DDL_AUTO = "hibernate.hbm2ddl.auto";
//    private static final String HIBERNATE_SHOW_SQL = "hibernate.showSql";

//    @Autowired
//    private Environment env;
//
//    @Bean
//    public DataSource dataSource() {
//        DruidDataSource source = new DruidDataSource();
//        source.setDriverClassName(env.getRequiredProperty(DATABASE_DRIVER));
//        source.setUrl(env.getRequiredProperty(DATABASE_URL));
//        source.setUsername(env.getRequiredProperty(DATABASE_USER));
//        source.setPassword(env.getRequiredProperty(DATABASE_PASSWORD));
//        return source;
//    }
//
//    @Bean
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory() {
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setDataSource(dataSource());
//        factory.setPersistenceProviderClass(HibernatePersistenceProvider.class);
//        factory.setPackagesToScan(env.getRequiredProperty(ENTITY_PACKAGES_TO_SCAN));
//        factory.setJpaProperties(hibernateProperties());
//        factory.afterPropertiesSet();
//        return factory;
//    }
//
//    @Bean
//    public PlatformTransactionManager transactionManager() {
//        JpaTransactionManager manager = new JpaTransactionManager();
//        manager.setEntityManagerFactory(entityManagerFactory().getObject());
//        return manager;
//    }
//
//    @Bean
//    public HibernateExceptionTranslator hibernateExceptionTranslator() {
//        return new HibernateExceptionTranslator();
//    }

//    private Properties hibernateProperties() {
//        Properties properties = new Properties();
//        properties.put("hibernate.dialect", env.getRequiredProperty(HIBERNATE_DIALECT));
//        properties.put("hibernate.show_sql", env.getRequiredProperty(HIBERNATE_SHOW_SQL));
//        properties.put("hibernate.hbm2ddl.auto", env.getRequiredProperty(HIBERNATE_HBM2DDL_AUTO));
//        return properties;
//    }

}
