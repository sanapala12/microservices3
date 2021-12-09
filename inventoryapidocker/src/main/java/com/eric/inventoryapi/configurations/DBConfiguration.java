package com.eric.inventoryapi.configurations;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Configuration
@Slf4j


public class DBConfiguration {
	
	@Value("${db_url}")
	private String dbUrl;
	@Value("${db_driver}")
	private String driver;
	@Value("${db_username}")
	private String dbUser;
	@Value("${db_password}")
	private String dbPassword;
	private DataSourceBuilder dataSourceBuilder;
    
    
    @Bean
   
    public DataSource getDataSource()
    {
    	log.info("Entering Given Env.....");
      
    	dataSourceBuilder=DataSourceBuilder.create();
    	dataSourceBuilder.url(dbUrl);
    	dataSourceBuilder.username(dbUser);
    	dataSourceBuilder.password(dbPassword);
    	dataSourceBuilder.driverClassName(driver);
    	return dataSourceBuilder.build();
   	
    }



}
