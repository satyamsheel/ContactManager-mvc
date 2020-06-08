package com.satyam.contacts.dao;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.satyam.contacts.model.Contacts;

public class contactsDAOTest {
	private DriverManagerDataSource datasource;
	private ContactDAO dao;
	

	@Test
	public void testSave() {
		
		datasource =new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		datasource.setUsername("root");
		datasource.setPassword("12345");
		
		dao=new ContactsDAOInti(datasource);
		
		
		Contacts contacts=new Contacts("Manpreet","amankumar@mail.com","gurugram,UP","1234567890");
		int result =dao.save(contacts);
		assertTrue(result>0);
		
	}

	@Test
	public void testUpdate() {
		datasource =new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		datasource.setUsername("root");
		datasource.setPassword("12345");
		
		dao=new ContactsDAOInti(datasource);
	
		Contacts contacts=new Contacts(6, "Manpreet","manpreet@gmail.com","Bhopal,M.P","12345678");
		int results = dao.update(contacts);
		assertTrue(results>0);
	}

	@Test
	public void testGet() {
		datasource =new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		datasource.setUsername("root");
		datasource.setPassword("12345");
		
		dao=new ContactsDAOInti(datasource);
		
		int id=1;
		Contacts contacts=dao.get(id);
		
		if(contacts!=null){
			System.out.println(contacts);
		}
		assertNotNull(contacts);
	}

	@Test
	public void testDelete() {
		datasource =new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		datasource.setUsername("root");
		datasource.setPassword("12345");
		
		dao=new ContactsDAOInti(datasource);
		
		int id=6;
		int result=dao.delete(id);
		
		assertTrue(result>0);
	}

	@Test
	public void testList() {
		
		datasource =new DriverManagerDataSource();
		datasource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		datasource.setUrl("jdbc:mysql://localhost:3306/contactdb");
		datasource.setUsername("root");
		datasource.setPassword("12345");
		
		dao=new ContactsDAOInti(datasource);
		List<Contacts> listcontacts=dao.list();
		
		for(Contacts aContacts :listcontacts){
			System.out.println(aContacts);
		}
		
		assertTrue(!listcontacts.isEmpty());
	}

}
