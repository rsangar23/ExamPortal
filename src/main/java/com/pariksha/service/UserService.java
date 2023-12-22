package com.pariksha.service;

import java.util.Set;

import com.pariksha.entity.User;
import com.pariksha.entity.UserRole;

public interface UserService {
	
	//create user
	
	public User createUser(User user, Set<UserRole> userRoles) throws Exception;
	
	//get user by username
	
	public User getUser(String name);
	
	//delete user by id
	
	public void deleteUser(Long id);
	
	// update user
	
	public User updateUser(Long id, User user);

}
