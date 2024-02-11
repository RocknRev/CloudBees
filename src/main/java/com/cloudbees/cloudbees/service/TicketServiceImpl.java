package com.cloudbees.cloudbees.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudbees.cloudbees.entity.Ticket;
import com.cloudbees.cloudbees.entity.Users;
import com.cloudbees.cloudbees.exception.ResourceNotFoundException;
import com.cloudbees.cloudbees.repository.TicketRepository;
import com.cloudbees.cloudbees.repository.UsersRepository;

@Service
public class TicketServiceImpl implements TicketService{

	@Autowired
	private TicketRepository ticketRepo;
	@Autowired
	private UsersRepository usersRepo;
	@Override
	public Ticket bookTicket(Ticket ticket, Long userId) {
		Optional<Users> userOptional = usersRepo.findById(userId);
		if(userOptional.isPresent()) {
			Ticket ticketToSave= new Ticket(ticket.getId(),ticket.getFromLoc(),ticket.getToLoc(),ticket.getPrice(),ticket.getSeatNumber(),ticket.getSection(),userOptional.get());
			return ticketRepo.save(ticketToSave);
		}else {
			throw new ResourceNotFoundException("Given User id: "+userId+" does not exists.");
		}
	}
	@Override
	public Ticket getBookedTicket(Long ticketId) {
		return ticketRepo.findById(ticketId).orElseThrow(
				()-> new ResourceNotFoundException("Given Ticket id: "+ticketId+" does not exists."));
	}

	@Override
	public List<Ticket> getAllBookedTickets() {
		return ticketRepo.findAll();
	}
	
	@Override
	public void deleteTicket(Long ticketId) {
		ticketRepo.findById(ticketId).orElseThrow(
				()-> new ResourceNotFoundException("Given Ticket id: "+ticketId+" does not exists."));
		ticketRepo.deleteById(ticketId);
	}
	
	@Override
	public Ticket modifyTicket(Long ticketId, String newSection, int newSeatNumber) {
		Optional<Ticket> ticketOptional = ticketRepo.findById(ticketId);
		if(ticketOptional.isPresent()) {
			Ticket modifiedTicket=ticketOptional.get();
			modifiedTicket.setSeatNumber(newSeatNumber);
			modifiedTicket.setSection(newSection);
			return ticketRepo.save(modifiedTicket);
		}else {
			throw new ResourceNotFoundException("Given Ticket id: "+ticketId+" does not exists.");
		}
	}
		
	@Override
	public List<Ticket> getTicketsBySection(String section) {
	        return ticketRepo.findBySection(section);
	}
	
}
