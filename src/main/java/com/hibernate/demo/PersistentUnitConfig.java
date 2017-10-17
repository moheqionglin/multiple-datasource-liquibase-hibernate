package com.hibernate.demo;

import com.hibernate.demo.domain.City;
import com.hibernate.demo.domain2.Classes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanli zhou
 * @created 2017-10-16 11:01 PM.
 */
@Configuration
public class PersistentUnitConfig {

    @Primary
    @Bean(name = "primaryEmFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean customerEntityManagerFactory(
            @Qualifier("primaryDataSource") DataSource ds,
            EntityManagerFactoryBuilder builder) {
        return builder
                .dataSource(ds)
                .packages(City.class)
                .persistenceUnit("primaryPersistenceUnit")
                .build();
    }

    @Bean(name = "primaryTransaction")
    public PlatformTransactionManager localTransactionManager(
            @Qualifier("primaryEmFactory") LocalContainerEntityManagerFactoryBean lc,
            @Qualifier("primaryDataSource") DataSource ds) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(lc.getObject());
        txManager.setDataSource(ds);
        return txManager;
    }
    @Bean(name = "primaryEntityManager")
    public EntityManager primaryEntityManager(
            @Qualifier("primaryEmFactory") LocalContainerEntityManagerFactoryBean le) {
        return le.getObject().createEntityManager();
    }

    public PersistenceExceptionTranslationPostProcessor exceptionTranslator() {
        return new PersistenceExceptionTranslationPostProcessor();
    }


    @Bean(name = "secondEmFactory")
    @Autowired
    public LocalContainerEntityManagerFactoryBean orderEntityManagerFactory(
            @Qualifier("secondDataSource") DataSource ds,
            EntityManagerFactoryBuilder builder) {
        System.out.println(ds);
        LocalContainerEntityManagerFactoryBean lcemf = builder
                .dataSource(ds)
                .packages(Classes.class)
                .persistenceUnit("secondPersistenceUnit")
                .properties(createEntityManagerProperties())
                .build();
        return lcemf;
    }
    @Bean(name = "secondEntityManager")
    public EntityManager secondEntityManager(
            @Qualifier("secondEmFactory") LocalContainerEntityManagerFactoryBean le) {
        return le.getObject().createEntityManager();
    }

    @Bean(name = "secondTransaction")
    public PlatformTransactionManager secondTransactionManager(
            @Qualifier("secondEmFactory") LocalContainerEntityManagerFactoryBean lc,
            @Qualifier("secondDataSource") DataSource ds) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(lc.getObject());
        txManager.setDataSource(ds);
        return txManager;
    }


    private Map<String, Object>  createEntityManagerProperties(){
        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.ejb.naming_strategy", org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl.class.getName());
        properties.put("hibernate.dialect", org.hibernate.dialect.MySQLDialect.class.getName());
//        properties.put("hibernate.hbm2ddl.auto", "validate");
        properties.put("hibernate.jdbc.use_get_generated_keys", true);
        properties.put("hibernate.id.new_generator_mappings", true);
        properties.put("hibernate.generate_statistics", false);
        properties.put("hibernate.format_sql", true);
        properties.put("hibernate.ddl-auto", false);
        properties.put("hibernate.naming.physical-strategy", org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl.class.getName());
        properties.put("hibernate.show_sql", true);
        return properties;
    }
}
