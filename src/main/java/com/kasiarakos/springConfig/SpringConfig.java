package com.kasiarakos.springConfig;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import com.kasiarakos.Dao.SupportJdbcDaoImpl;

@Configuration
@ComponentScan(basePackages={"com.kasiarakos"})
public class SpringConfig {
	
	
	@Bean(name="dataSource")
	DataSource dataSource(){
		BasicDataSource dm = new BasicDataSource();
//		DriverManagerDataSource dm = new DriverManagerDataSource();
		dm.setDriverClassName("com.mysql.jdbc.Driver");
		dm.setUrl("jdbc:mysql://localhost:3306/db");
		dm.setUsername("root");
		dm.setPassword("root");
		dm.setInitialSize(2);
		dm.setMaxActive(5);
		
		return dm;
	}
	
	@Bean
	JdbcTemplate jdbcTemplate(DataSource dataSource){
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource);
		return template;
	}
	
	@Bean
	NamedParameterJdbcTemplate namedPatameterJdbcTemplate(DataSource dataSource){
		return new NamedParameterJdbcTemplate(dataSource);
	}
	
	@Bean
	SupportJdbcDaoImpl supportJdbcDaoImpl(DataSource dataSource){
		SupportJdbcDaoImpl dao = new SupportJdbcDaoImpl();
		dao.setDataSource(dataSource);
		return dao;
	}
	
}
