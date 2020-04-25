package com.caseyteknology.model;

import java.util.List;

public interface UserDAO {
	
	void createNewUser(User user);
	
	void deleteUser(String email);
	
	void updateUser(User user);
	
	List<User> getAllUsers();
	
	User getUserByEmail(String email);

}
