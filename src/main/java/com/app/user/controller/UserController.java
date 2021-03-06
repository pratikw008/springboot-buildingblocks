package com.app.user.controller;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.user.exceptions.UserExistsException;
import com.app.user.exceptions.UserNotFoundException;
import com.app.user.model.UserEntity;
import com.app.user.service.UserService;

@RestController
@RequestMapping("/users")
@Validated
public class UserController {
	
	private UserService userService;

	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}
	
	@GetMapping
	public List<UserEntity> getAllUsers() {
		return userService.getAllUsers();
	}
	
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE,
				 produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserEntity> createUser(@Valid @RequestBody UserEntity userEntity) {
		UserEntity savedInDB;
		try {
			savedInDB = userService.createUser(userEntity);
		} catch (UserExistsException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
		
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedInDB.getId()).toUri();
		return ResponseEntity.created(location).body(savedInDB);
	}
	
	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable("id") @Min(1) int id) {
		try {			
			return userService.getUserById(id);
		}
		catch (UserNotFoundException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUserById(@PathVariable("id") int id,@RequestBody UserEntity userEntity) {
		try {
			return userService.updateUserById(id, userEntity);
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") int id) {
		try {
			return userService.deleteUserById(id);
		}
		catch(UserNotFoundException ex) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ex.getMessage());
		}
	}
	
	@GetMapping("/byUsername/{username}")
	public UserEntity findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
}
