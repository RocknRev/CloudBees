package com.cloudbees.cloudbees.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudbees.cloudbees.entity.Users;
import com.cloudbees.cloudbees.exception.ResourceNotFoundException;
import com.cloudbees.cloudbees.repository.UsersRepository;

@Service
public class UsersServiceImpl implements UsersService{

	@Autowired
	private UsersRepository userRepo;
	@Override
	public Users createUser(Users user) {
		Users savedUser=new Users(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
		return userRepo.save(savedUser);
	}
	@Override
	public Users getUserById(Long userId) {
		return userRepo.findById(userId).orElseThrow(
				()->new ResourceNotFoundException("Given User id: "+userId+" does not exists."));
	}
	@Override
	public List<Users> getAllUsers() {
		return userRepo.findAll();
	}
	

}
