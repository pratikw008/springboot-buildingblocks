package com.app.user.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.app.user.model.UserEntity;
import com.app.user.repository.UserRepository;
import com.app.user.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	}

	@Override
	public List<UserEntity> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public UserEntity createUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity getUserById(int id) {
		return userRepository.findById(id).get();
	}

	@Override
	public UserEntity updateUserById(int id, UserEntity userEntity) {

		return userRepository.findById(id).map(entityFromDb -> {
			if(userEntity.getEmail() != null)
				entityFromDb.setEmail(userEntity.getEmail());

			if(userEntity.getFirstName() != null)
				entityFromDb.setFirstName(userEntity.getFirstName());

			if(userEntity.getLastName() != null)
				entityFromDb.setLastName(userEntity.getLastName());

			if(userEntity.getRole() != null) 
				entityFromDb.setRole(userEntity.getRole());
			
			return userRepository.save(entityFromDb); 
		}).orElseThrow(() -> new RuntimeException("Plz Provide Valid ID"));
	}
	
	@Override
	public String deleteUserById(int id) {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "ID:"+id+" Deleted";
		}
		else
			return "Plz Provide Valid ID:"+id;
	}
}
