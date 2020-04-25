/**
 * 
 */
package com.caseyteknology.model;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.xml.bind.DatatypeConverter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

@Component
public class JDBCUserDAO implements UserDAO {

    private JdbcTemplate jdbcTemplate;

 
    @Autowired
    public JDBCUserDAO(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
	
	@Override
	public void createNewUser(User user) {
		String sql = "INSERT INTO users(id, firstname, lastname, email, phonenumber) VALUES(?,?,?,?,?)";
		
		//retrieve user email, set it to lower case to make sure user isn't registering with
		//the same email and different capitalization
		String email =  user.getEmail().toLowerCase();
		user.setEmail(email);
		
		//catches exception if unable to encode email
		try {
			user.setId(toHexadecimal(email));
		} catch (UnsupportedEncodingException e) {
			System.out.println("Unable to encode email");
		}
	
		//if the ID was successfully encoded, let's try and save to the database
		if(user.getId() != null) {
		jdbcTemplate.update(sql, user.getId(), user.getFirstName(), user.getLastName(),
				user.getEmail(), user.getPhoneNumber());
		}
	}
 
	@Override
	public void deleteUser(String email) {
		String sql = "DELETE FROM users WHERE email = ?";
		jdbcTemplate.update(sql, email);

	}

	@Override
	public void updateUser(User user) {
		String sql = "UPDATE users SET firstname = ?, lastname = ?, phonenumber = ? WHERE email = ?";
		jdbcTemplate.update(sql, user.getFirstName(), user.getLastName(), user.getPhoneNumber(), user.getEmail());
	}

	@Override
	public List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		String sql = "SELECT * FROM users";
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
		while(results.next()) {
			User user = mapRowToUser(results);
			users.add(user);
		}
		return users;
	}

	@Override
	public User getUserByEmail(String email) {
		String sql = "SELECT * FROM users WHERE email = ?";
		User user = new User();
		SqlRowSet results = jdbcTemplate.queryForRowSet(sql, email);
		if(results.next()) {
			user = mapRowToUser(results);
		}
		return user;
	}
	
	private User mapRowToUser(SqlRowSet results) {
		User user = new User();
		user.setId(results.getString("id"));
		user.setFirstName(results.getString("firstname"));
		user.setLastName(results.getString("lastname"));
		user.setEmail(results.getString("email"));
		user.setPhoneNumber(results.getString("phonenumber"));
		return user;
	}
	
	//helper code to generate a hexadecimal ID from user's email
	private static String toHexadecimal(String email) throws UnsupportedEncodingException
	{
	    byte[] myBytes = email.getBytes("UTF-8");

	    return DatatypeConverter.printHexBinary(myBytes);
	}

}
