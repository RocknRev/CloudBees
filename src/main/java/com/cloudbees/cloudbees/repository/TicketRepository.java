package com.cloudbees.cloudbees.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cloudbees.cloudbees.entity.Ticket;
//import com.cloudbees.cloudbees.entity.Users;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long>{
	List<Ticket> findBySection(String section);
}
