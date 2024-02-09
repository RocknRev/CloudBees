package com.cloudbees.cloudbees.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudbees.cloudbees.entity.Users;

public interface UsersRepository extends JpaRepository<Users, Long>{

}
