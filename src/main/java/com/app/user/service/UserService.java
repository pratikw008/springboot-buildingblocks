package com.app.user.service;

import java.util.List;

import com.app.user.model.UserEntity;

public interface UserService {

	public List<UserEntity> getAllUsers();
	
	public UserEntity createUser(UserEntity userEntity);
	
	public UserEntity getUserById(int id);
	
	public UserEntity updateUserById(int id, UserEntity userEntity);
	
	public String deleteUserById(int id);
}
