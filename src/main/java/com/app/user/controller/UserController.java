package com.app.user.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.app.user.model.UserEntity;
import com.app.user.service.UserService;

@RestController
@RequestMapping("/users")
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
	public ResponseEntity<UserEntity> createUser(@RequestBody UserEntity userEntity) {
		UserEntity savedInDB = userService.createUser(userEntity);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(savedInDB.getId()).toUri();
		return ResponseEntity.created(location).body(savedInDB);
	}
	
	@GetMapping("/{id}")
	public UserEntity getUserById(@PathVariable("id") int id) {
		return userService.getUserById(id);
	}
	
	@PutMapping("/{id}")
	public UserEntity updateUserById(@PathVariable("id") int id,@RequestBody UserEntity userEntity) {
		return userService.updateUserById(id, userEntity);
	}
	
	@DeleteMapping("/{id}")
	public String deleteById(@PathVariable("id") int id) {
		return userService.deleteUserById(id);
	}
	
	@GetMapping("/byUsername/{username}")
	public UserEntity findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}
}
