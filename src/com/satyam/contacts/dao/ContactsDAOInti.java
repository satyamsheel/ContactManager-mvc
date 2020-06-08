package com.satyam.contacts.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.satyam.contacts.model.Contacts;

public class ContactsDAOInti implements ContactDAO {
	private JdbcTemplate jdbcTemplate;
	
	public ContactsDAOInti(DataSource datasource){
		this.jdbcTemplate=new JdbcTemplate(datasource);
	}

	@Override
	public int save(Contacts c) {
		String sql="INSERT INTO contact(name,email,address,phone) VALUES (?,?,?,?)";
		return  jdbcTemplate.update(sql,c.getName(),c.getEmail(),c.getAddress(),c.getPhone());
		
	}

	@Override
	public int update(Contacts c) {
	String sql="UPDATE contact SET name=?,email=?,address=?,phone=? WHERE contact_id=?";
	return jdbcTemplate.update(sql,c.getName(),c.getEmail(),c.getAddress(),c.getPhone(),c.getId());
	}

	@Override
	public Contacts get(int id) {

		String sql="SELECT * FROM contact WHERE contact_id="+id;
		
		ResultSetExtractor<Contacts> extractor=new ResultSetExtractor<Contacts>(){

			@Override
			public Contacts extractData(ResultSet rs) throws SQLException, DataAccessException {
				if(rs.next()){
					String name=rs.getString("name");
					String email=rs.getString("email");
					String address=rs.getString("address");
					String phone=rs.getString("phone");
					
					return new Contacts(id,name,email,address,phone);
				}
				return null;
			}
			
		};
		return jdbcTemplate.query(sql, extractor);
	}

	@Override
	public int delete(int id) {
		String sql="DELETE from contact WHERE contact_id="+id;
		
		return jdbcTemplate.update(sql);
	}

	@Override
	public List<Contacts> list() {
		String sql="SELECT * FROM contact";
		RowMapper<Contacts> rowMapper=new RowMapper<Contacts>(){

			@Override
			public Contacts mapRow(ResultSet rs, int rowNum) throws SQLException {
				Integer id=rs.getInt("contact_id");
				String name=rs.getString("name");
				String email=rs.getString("email");
				String address=rs.getString("address");
				String phone=rs.getString("phone");
				
				return new Contacts(id,name,email,address,phone);
			}
			
		};
		return jdbcTemplate.query(sql, rowMapper);
		
	}

}
