package com.caseyteknology.model;

public class test {

	public static void main(String[] args) {

		User user = new User();
		user.setFirstName("Jon");
		user.setLastName("Bon");
		user.setPhoneNumber("123");
		user.setEmail("jonBon@gmail.com");
		user.setId("fakeId");
		System.out.println(user.toString());
	}

}
