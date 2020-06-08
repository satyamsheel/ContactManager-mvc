package com.satyam.contacts.dao;

import java.util.List;

import com.satyam.contacts.model.Contacts;

public interface ContactDAO {
	
	public int save(Contacts contact);
	
	public int update(Contacts contact);
	
	public Contacts get(int id);
	
	public int delete(int id);
	
	public List<Contacts>list();
	
	
	
	

}
