package com.app.user.service;

import java.util.List;

import com.app.user.exceptions.UserNotFoundException;
import com.app.user.model.UserEntity;

public interface UserService {

	public List<UserEntity> getAllUsers();
	
	public UserEntity createUser(UserEntity userEntity);
	
	public UserEntity getUserById(int id) throws UserNotFoundException;
	
	public UserEntity updateUserById(int id, UserEntity userEntity) throws UserNotFoundException;
	
	public String deleteUserById(int id) throws UserNotFoundException;
	
	public UserEntity findByUsername(String username);
}
