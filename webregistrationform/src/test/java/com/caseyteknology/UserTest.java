package com.caseyteknology;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.caseyteknology.model.User;

public class UserTest {

	@Test
	public void generate_toString_and_compare() {
		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setEmail("jonBon@gmail.com");
		user.setId("fakeId");
		String expectedToString = "User [id=fakeId, firstName=Jon, lastName=Bon, email=jonBon@gmail.com, phoneNumber=123]";
		assertEquals(expectedToString, user.toString());
	}
	
	@Test
	public void make_two_equal_users__with_different_first_names_and_compare() {
		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setEmail("jonBon@gmail.com");
		user.setId("fakeId");
		User user2 = new User();
		user2.setFirstName("Bon");
		user2.setLastName("Bon");
		user2.setPhoneNumber("123");
		user2.setEmail("jonBon@gmail.com");
		user2.setId("fakeId");
	assertEquals(user.equals(user2), false);
	}
	
	@Test
	public void make_two_equal_users_with_different_ids_and_compare() {
		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setEmail("jonBon@gmail.com");
		user.setId("fakeId");
		User user2 = new User();
		user2.setFirstName("Jon");
		user2.setLastName("Bon");
		user2.setPhoneNumber("123");
		user2.setEmail("jonBon@gmail.com");
		user2.setId("anotherFakeId");
	assertEquals(user.equals(user2), false);
	}
	
	@Test
	public void make_two_equal_users_with_different_phone_numbers_and_compare() {
		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setEmail("jonBon@gmail.com");
		user.setId("fakeId");
		User user2 = new User();
		user2.setFirstName("Jon");
		user2.setLastName("Bon");
		user2.setPhoneNumber("1234");
		user2.setEmail("jonBon@gmail.com");
		user2.setId("fakeId");
	assertEquals(user.equals(user2), false);
	}
	
	@Test
	public void make_two_equal_users_and_compare() {
		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setEmail("jonBon@gmail.com");
		user.setId("fakeId");
		User user2 = new User();
		user2.setFirstName("Jon");
		user2.setLastName("Bon");
		user2.setPhoneNumber("123");
		user2.setEmail("jonBon@gmail.com");
		user2.setId("fakeId");
	assertEquals(user.equals(user2), true);
	}
	
	@Test
	public void compare_two_users_with_one_null() {
		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setId("fakeId");
		User user2 = new User();
		user2.setFirstName("Jon");
		user2.setLastName("Bon");
		user2.setPhoneNumber("123");
		user2.setEmail("jonBon@gmail.com");
	assertEquals(user.equals(user2), false);
	}
}
