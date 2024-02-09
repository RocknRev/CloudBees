package com.cloudbees.cloudbees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cloudbees.cloudbees.entity.Users;
import com.cloudbees.cloudbees.service.UsersServiceImpl;

@RestController
@RequestMapping("api/users")
public class UserController {
	@Autowired
	private UsersServiceImpl userService;
	
	@PostMapping
	public ResponseEntity<Users> createUser(@RequestBody Users user){
		Users savedUser = userService.createUser(user);
		return new ResponseEntity<>(savedUser,HttpStatus.CREATED);
	}
	
	@GetMapping("{userId}")
	public ResponseEntity<Users> getUser(@PathVariable Long userId){
		Users user= userService.getUserById(userId);
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Users>> getUsers(){
		List<Users> user= userService.getAllUsers();
		return new ResponseEntity<>(user,HttpStatus.OK);
	}
}
