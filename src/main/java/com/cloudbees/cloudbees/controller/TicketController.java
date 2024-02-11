package com.cloudbees.cloudbees.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudbees.cloudbees.entity.Ticket;
import com.cloudbees.cloudbees.service.TicketServiceImpl;

@RestController
@RequestMapping("/api/ticket")
public class TicketController {

	@Autowired
	private TicketServiceImpl ticketService;
	
	@PostMapping("{userId}")
	public ResponseEntity<Ticket> bookTicket(@RequestBody Ticket ticket, @PathVariable Long userId){
		Ticket bookedTicket = ticketService.bookTicket(ticket, userId);
		return new ResponseEntity<>(bookedTicket,HttpStatus.CREATED);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<Ticket> showBookedTicket(@PathVariable Long id){
		Ticket ticket = ticketService.getBookedTicket(id);
		return new ResponseEntity<>(ticket,HttpStatus.OK);
	}
	
	@GetMapping("section-wise")
    	public ResponseEntity<List<Ticket>> getTicketsBySection(@RequestParam String section) {
		return new ResponseEntity<>(ticketService.getTicketsBySection(section),HttpStatus.OK);
    	}
	
	@GetMapping
	public ResponseEntity<List<Ticket>> showAllBookedTickets(){
		List<Ticket> ticket = ticketService.getAllBookedTickets();
		return new ResponseEntity<>(ticket,HttpStatus.OK);
	}
	
	@DeleteMapping("{ticketId}")
	public ResponseEntity<String> deleteTicket(@PathVariable Long ticketId){
		ticketService.deleteTicket(ticketId);
		return new ResponseEntity<>("Ticket Deleted Successfully.",HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<Ticket> modifyTicket(@RequestParam Long ticketId, @RequestParam String newSection, @RequestParam int newSeatNumber){
		Ticket modifiedTicket = ticketService.modifyTicket(ticketId, newSection, newSeatNumber);
		return new ResponseEntity<>(modifiedTicket,HttpStatus.OK);
	}
}
