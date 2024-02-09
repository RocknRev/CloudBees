package com.cloudbees.cloudbees;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import com.cloudbees.cloudbees.entity.Ticket;
import com.cloudbees.cloudbees.entity.Users;
import com.cloudbees.cloudbees.repository.TicketRepository;
import com.cloudbees.cloudbees.repository.UsersRepository;
import com.cloudbees.cloudbees.service.TicketServiceImpl;

@SpringBootTest
public class CloudbeesApplicationTests {

    @Mock
    private UsersRepository userRepository;

    @Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketServiceImpl ticketService;

    @Test
    public void testPurchaseTicket() {
        Users mockUser = new Users(1L,"Rak","N","rak@gmail.com");
        when(userRepository.findById(anyLong())).thenReturn(Optional.of(mockUser));

        Ticket mockTicket = new Ticket(1L,"From","To",20.0,19,"A",mockUser);
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);

        Ticket purchasedTicket = ticketService.bookTicket(mockTicket, 1L);

        assertEquals(1L, purchasedTicket.getId());
        assertEquals("From", purchasedTicket.getFromLoc());
        assertEquals("To", purchasedTicket.getToLoc());
        assertEquals(20.0, purchasedTicket.getPrice());
        assertEquals("A", purchasedTicket.getSection());
        assertEquals(19, purchasedTicket.getSeatNumber());
        assertEquals(mockUser, purchasedTicket.getUsers());

        verify(userRepository, times(1)).findById(anyLong());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    public void testGetTicketDetails() {
        Ticket mockTicket = new Ticket();
        mockTicket.setId(1L);
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(mockTicket));

        Ticket ticketDetails = ticketService.getBookedTicket(1L);

        assertEquals(1L, ticketDetails.getId());

        verify(ticketRepository, times(1)).findById(anyLong());
    }

    @Test
    public void testGetTicketsBySection() {
    	Users mockUser = new Users(1L,"Rak","N","rak@gmail.com");
        Ticket mockTicket = new Ticket(1L,"London","France",20.0,19,"A",mockUser);
        when(ticketRepository.findBySection(anyString())).thenReturn(Collections.singletonList(mockTicket));

        List<Ticket> ticketsBySection = ticketService.getTicketsBySection("A");

        assertEquals(1, ticketsBySection.size());
        assertEquals(1L, ticketsBySection.get(0).getId());

        verify(ticketRepository, times(1)).findBySection(anyString());
    }

    @Test
    public void testModifyTicketSeat() {
    	Users mockUser = new Users(1L,"Rak","N","rak@gmail.com");
        Ticket mockTicket = new Ticket(1L,"From","To",20.0,19,"A",mockUser);

        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(mockTicket));
        when(ticketRepository.save(any(Ticket.class))).thenReturn(mockTicket);

        ticketService.modifyTicket(1L, "B", 2);

        assertEquals("B", mockTicket.getSection());
        assertEquals(2, mockTicket.getSeatNumber());

        verify(ticketRepository, times(1)).findById(anyLong());
        verify(ticketRepository, times(1)).save(any(Ticket.class));
    }

    @Test
    public void testRemoveTicket() {
    	Users mockUser = new Users(1L,"Rak","N","rak@gmail.com");
        Ticket mockTicket = new Ticket(1L,"From","To",20.0,19,"A",mockUser);
        when(ticketRepository.findById(anyLong())).thenReturn(Optional.of(mockTicket));

        ticketService.deleteTicket(1L);
        
        verify(ticketRepository, times(1)).findById(anyLong());
        verify(ticketRepository, times(1)).deleteById(anyLong());
    }

}
