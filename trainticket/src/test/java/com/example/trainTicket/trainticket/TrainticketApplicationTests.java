package com.example.trainTicket.trainticket;

import java.util.Optional;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.trainTicket.entity.Ticket;
import com.example.trainTicket.repository.TicketRepository;
import com.example.trainTicket.service.TicketService;


@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TrainticketApplicationTests {

	@Mock
    private TicketRepository ticketRepository;

    @InjectMocks
    private TicketService ticketService;

    @Test
    public void testModifyUserSeat() {
    	
    	
    	  Long userId = 1L;
          int newSeatNum = 15;
          Ticket existingTicket = new Ticket(); // Create a Ticket with some data

          // Mock the behavior of ticketRepository
          Mockito.when(ticketRepository.findById(userId)).thenReturn(Optional.of(existingTicket));
          Mockito.when(ticketRepository.existsBySeatNum(newSeatNum)).thenReturn(false);
          Mockito.when(ticketRepository.save(Mockito.any(Ticket.class))).thenReturn(existingTicket);


          ResponseEntity<Ticket> response = ticketService.modifyUserSeat(userId, newSeatNum);


          Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
          Assert.assertNotNull(response.getBody());
          Assert.assertEquals(newSeatNum, response.getBody().getSeatNum());
      }
}
