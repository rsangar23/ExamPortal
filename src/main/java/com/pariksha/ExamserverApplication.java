package com.pariksha;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.pariksha.entity.Role;
import com.pariksha.entity.User;
import com.pariksha.entity.UserRole;
import com.pariksha.service.UserService;

@SpringBootApplication
public class ExamserverApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;
	
	public static void main(String[] args) {
		SpringApplication.run(ExamserverApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Starting code...");
		
//		User user = new User();
//		
//		user.setFirstName("Rohit");
//		user.setLastName("Sangar");
//		user.setEmail("rohit@gmail.com");
//		user.setPhone("9090909090");
//		user.setUserName("rsangar23");
//		user.setPassword("abc");
//		user.setProfile("default.png");
//		
//		Role role = new Role();
//		role.setRoleId(23L);
//		role.setRoleName("Admin");
//		
//		Set<UserRole> userRoleSet = new HashSet<>();
//		UserRole userRole = new UserRole();
//		userRole.setRole(role);
//		userRole.setUser(user);
//		
//		userRoleSet.add(userRole);
//		
//		User user1 = this.userService.createUser(user, userRoleSet);
//		System.out.println(user1.getUserName());
	}

}
