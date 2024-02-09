package com.cloudbees.cloudbees.service;

import java.util.List;

import com.cloudbees.cloudbees.entity.Ticket;

public interface TicketService {
	Ticket bookTicket(Ticket ticket, Long userId);
	Ticket getBookedTicket(Long ticketId);
	List<Ticket> getAllBookedTickets();
	
	void deleteTicket(Long ticketId);
	Ticket modifyTicket(Long ticketId, String newSection, int newSeatNumber);
	List<Ticket> getTicketsBySection(String section);
}
