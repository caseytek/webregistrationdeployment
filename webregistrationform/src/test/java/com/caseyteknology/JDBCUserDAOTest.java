package com.caseyteknology;

import static org.junit.Assert.assertEquals;

import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.xml.bind.DatatypeConverter;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

import com.caseyteknology.model.JDBCUserDAO;
import com.caseyteknology.model.User;
import com.caseyteknology.model.UserDAO;

public class JDBCUserDAOTest {
	
	private static SingleConnectionDataSource dataSource;
	private UserDAO userDao;
	
	@BeforeClass
	public static void setupDataSource() {
		dataSource = new SingleConnectionDataSource();
		dataSource.setUrl("jdbc:postgresql://localhost:5432/webRegistration");
		dataSource.setUsername("postgres");
		dataSource.setPassword("postgres1");
		dataSource.setAutoCommit(false);
	}
	
	@AfterClass
	public static void closeDataSource() throws SQLException {
		dataSource.destroy();
	}
	
	@Before
	public void setup() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		userDao = new JDBCUserDAO(dataSource);
		String testSql = "INSERT INTO users (id, firstname, lastname, email, phonenumber) VALUES ('unencoded id', 'John', 'Smith', 'smithjohn@gmail.com', '222-222-2222')";
		jdbcTemplate.update(testSql);
	}
	
	@After
	public void rollback() throws SQLException {
		dataSource.getConnection().rollback();
	}

	@Test
	public void add_new_user_and_compare_compare_list_lengths() {
		int numberOfUsersPre = userDao.getAllUsers().size();
		User newUser = getUser("id", "Jane", "Doe", "DoeJane@gmail.com", "333-333-3333");
		userDao.createNewUser(newUser);
		int numberOfUsersPost = userDao.getAllUsers().size();
		assertEquals(numberOfUsersPre + 1, numberOfUsersPost);
	}
	
	@Test 
	public void create_user_and_compare_expected_name_to_actual() {
		User user = userDao.getUserByEmail("smithjohn@gmail.com");
		assertEquals("smithjohn@gmail.com", user.getEmail());
	}
	
	@Test
	public void update_user_and_read_back_update() {
		User user = userDao.getUserByEmail("smithjohn@gmail.com");
		user.setFirstName("Johnny");
		userDao.updateUser(user);
		assertEquals("Johnny", userDao.getUserByEmail("smithjohn@gmail.com").getFirstName());
	}
	
	@Test
	public void delete_user_and_try_to_find_same_user() {
		userDao.deleteUser("smithjohn@gmail.com");
		List<User> allUsers = userDao.getAllUsers();
		boolean foundFlag = false;
		for(User user: allUsers) {
			if(user.getEmail() == "smithjohn@gmail.com") {
				foundFlag = true;
			}
		}
		assertEquals(foundFlag, false);
	}
	
	@Test
	public void generate_hexadecimal_id_and_compare_to_expected() throws UnsupportedEncodingException {
		String email = "tekcasey@gmail.com";
		byte[] myBytes = email.getBytes("UTF-8");
		String id =  DatatypeConverter.printHexBinary(myBytes);
		assertEquals("74656B636173657940676D61696C2E636F6D", id);
	}
	
	private User getUser(String id, String firstName, String lastName, String email, String phoneNumber) {
		User user = new User();
		user.setId(id);
		user.setFirstName(firstName);
		user.setLastName(lastName);;
		user.setEmail(email);
		user.setPhoneNumber(phoneNumber);
		return user;
	} 

}
