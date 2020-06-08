package com.satyam.contacts.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import com.satyam.contacts.dao.ContactDAO;
import com.satyam.contacts.dao.ContactsDAOInti;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages="com.satyam.contacts")
public class SpringMvcConfig implements WebMvcConfigurer {
	
	@Bean
	public DataSource getDataSource(){
		DriverManagerDataSource datasource =new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		datasource.setUsername("root");
		datasource.setPassword("12345");
				
		return datasource;
	}
	
	@Bean
	public ViewResolver getViewResolver(){
		InternalResourceViewResolver resolver=new InternalResourceViewResolver();
		resolver.setPrefix("/WEB-INF/views/");
		resolver.setSuffix(".jsp");
		
		return resolver;
	}
	
	@Bean
	public ContactDAO getContactDao(){

		ContactDAO dao = new ContactsDAOInti(getDataSource());
		return dao;
	}
	

}
