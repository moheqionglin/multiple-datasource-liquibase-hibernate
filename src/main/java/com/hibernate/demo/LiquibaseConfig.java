package com.hibernate.demo;

import liquibase.integration.spring.SpringLiquibase;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseProperties;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @author wanli zhou
 * @created 2017-10-16 9:45 PM.
 */
@Configuration
public class LiquibaseConfig {

    //---------------PRIMARY -----------------
    @Primary
    @Bean(name = "primaryDataSource")
    @ConfigurationProperties(prefix = "multiple.datasource.ds1")
    public DataSource primaryDataSource() {
        DataSource ds = DataSourceBuilder.create().build();
        System.out.println("===========================primaryDataSource================");
        System.out.println(ds);
        System.out.println("============================================================");

        return ds;
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.ds1.liquibase")
    public LiquibaseProperties primaryLiquibasePropertiese(){
        return new LiquibaseProperties();
    }

    @Bean(name = "liquibase")
    public SpringLiquibase primaryLiquibase(){
        return createSpringLiquibase(primaryDataSource(), primaryLiquibasePropertiese());
    }

    //---------------SECOND -----------------
    @Bean(name = "secondDataSource")
    @ConfigurationProperties(prefix = "multiple.datasource.ds2")
    public DataSource secondDataSource() {
        DataSource ds2 = DataSourceBuilder.create().build();
        System.out.println("===========================secondDataSource================");
        System.out.println(ds2);
        System.out.println("============================================================");
        return ds2;
    }

    @Bean
    @ConfigurationProperties(prefix = "multiple.datasource.ds2.liquibase")
    public LiquibaseProperties secondLiquibasePropertiese(){
        return new LiquibaseProperties();
    }

    @Bean
    public SpringLiquibase secondLiquibase(){
        return createSpringLiquibase(secondDataSource(), secondLiquibasePropertiese());
    }


    private SpringLiquibase createSpringLiquibase(DataSource ds, LiquibaseProperties properties){
        SpringLiquibase liquibase = new SpringLiquibase();
        liquibase.setDataSource(ds);
        liquibase.setChangeLog(properties.getChangeLog());
        liquibase.setContexts(properties.getContexts());
        liquibase.setDefaultSchema(properties.getDefaultSchema());
        liquibase.setDropFirst(properties.isDropFirst());
        liquibase.setShouldRun(properties.isEnabled());
        liquibase.setLabels(properties.getLabels());
        liquibase.setChangeLogParameters(properties.getParameters());
        liquibase.setRollbackFile(properties.getRollbackFile());
        return liquibase;
    }
}
