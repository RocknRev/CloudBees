package com.cloudbees.cloudbees.service;

import java.util.List;

import com.cloudbees.cloudbees.entity.Users;

public interface UsersService {
	Users createUser(Users user);
	Users getUserById(Long userId);
	List<Users> getAllUsers();
}
