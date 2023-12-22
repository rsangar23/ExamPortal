package com.pariksha.service.impl;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pariksha.entity.User;
import com.pariksha.entity.UserRole;
import com.pariksha.helper.UserFoundException;
import com.pariksha.helper.UserNotFoundException;
import com.pariksha.repository.RoleRepository;
import com.pariksha.repository.UserRepository;
import com.pariksha.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	//create user
	
	@Override
	public User createUser(User user, Set<UserRole> userRoles) throws Exception {
		
		User local = this.userRepository.findByUsername(user.getUsername());
		if(local != null) {
			System.out.println("User is already there..!!");
			throw new UserFoundException();
			
		}else {
			
			for(UserRole ur : userRoles) {
				roleRepository.save(ur.getRole());
				
				user.getUserRoles().addAll(userRoles);
				local = this.userRepository.save(user);
			}
			
		}
		
		return local;
	}

	// getting user by username
	
	@Override
	public User getUser(String name) {
		
		return this.userRepository.findByUsername(name);
	}

	// delete user by id
	
	@Override
	public void deleteUser(Long id) {
		this.userRepository.deleteById(id);
	}
	
	// update user
	@Override
	public User updateUser(Long id, User user) {
		
		Optional<User> user1 = userRepository.findById(id);
		
		User user2 = user1.get();
		user2.setFirstName(user.getFirstName());
		user2.setLastName(user.getLastName());
		user2.setEmail(user.getEmail());
		user2.setPhone(user.getPhone());
		user2.setProfile(user.getProfile());
		user2.setUsername(user.getUsername());
				
		return this.userRepository.save(user2);
	}

}
