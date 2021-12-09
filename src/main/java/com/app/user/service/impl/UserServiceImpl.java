package com.app.user.service.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.Function;

import org.springframework.stereotype.Service;

import com.app.user.exceptions.UserExistsException;
import com.app.user.exceptions.UserNameNotFoundException;
import com.app.user.exceptions.UserNotFoundException;
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
	public UserEntity createUser(UserEntity userEntity) throws UserExistsException {
		
		Optional<UserEntity> existingUser = userRepository.findByUsername(userEntity.getUsername());
		if(existingUser.isPresent()) {
			throw new UserExistsException("User Already Exists In DB");
		}
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity getUserById(int id) throws UserNotFoundException {
		return userRepository.findById(id)
							 .map(Function.identity())
							 .orElseThrow(() -> new UserNotFoundException("User Not Found In DB"));
	}

	@Override
	public UserEntity updateUserById(int id, UserEntity userEntity) throws UserNotFoundException {

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
		}).orElseThrow(() -> new UserNotFoundException("Plz Provide Valid ID"));
	}
	
	@Override
	public String deleteUserById(int id) throws UserNotFoundException {
		if(userRepository.existsById(id)) {
			userRepository.deleteById(id);
			return "ID:"+id+" Deleted";
		}
		else
			throw new UserNotFoundException("Plz Provide Valid ID");
	}
	
	@Override
	public UserEntity findByUsername(String username) {
		return userRepository.findByUsername(username)
							 .map(Function.identity())
							 .orElseThrow(() -> new UserNameNotFoundException("UserName::"+username+" Not Found In DB"));
	}
}
