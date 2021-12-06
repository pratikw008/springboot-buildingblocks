package com.app.user.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.app.user.model.UserEntity;
import com.app.user.repository.UserRepository;

@SpringBootTest
class UserServiceImplTest {
	
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	@MockBean
	private UserRepository userRepository;
	
	@Test
	public void contextLoads() {
		assertThat(userServiceImpl).isNotNull();
		assertThat(userRepository).isNotNull();
	}
	
	@Test
	public void testGetAllUsers() {
		when(userRepository.findAll()).thenReturn(usersList());
		List<UserEntity> allUsers = userServiceImpl.getAllUsers();
		assertThat(allUsers).isNotEmpty();
		assertEquals(allUsers.size(), 2);
	}
	
	@Test
	public void testCreateUser() {
		UserEntity user = getUser();
		
		when(userRepository.save(user)).thenReturn(user);
		UserEntity createdUser = userServiceImpl.createUser(user);
		System.out.println(createdUser);
		//assertThat(createdUser).isNotNull();
		assertEquals(createdUser, user);
	}

	private List<UserEntity> usersList() {
		return Arrays.asList(new UserEntity(101, "vkohli", "Virat", "Kohli", "vk@gmailcom", "admin", "ssn101"),
							 new UserEntity(102, "rosharma", "Rohit", "Sharma", "hitman@gmailcom", "admin", "ssn102"));
	}
	
	private UserEntity getUser() {
		return new UserEntity(1001, "skraina", "Suresh", "Raina", "sk@gmailcom", "admin", "ssn1001");
	}
}
