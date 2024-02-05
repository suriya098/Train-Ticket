package com.example.trainTicket.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.trainTicket.entity.Ticket;
import com.example.trainTicket.entity.User;
import com.example.trainTicket.repository.TicketRepository;
import com.example.trainTicket.request.PurchaseRequest;

import jakarta.transaction.Transactional;

@Service
public class TicketService {
	
	private final TicketRepository ticketRepository;

    @Autowired
    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }
    
    
    @Transactional
    public ResponseEntity<?> purchaseTicket(PurchaseRequest purchaseRequest) { 
    	
    	User user = purchaseRequest.getUser();
	 	user.setAllocatedSection(purchaseRequest.getAllocatedSection());
	 	
	 	Ticket newTicket = new Ticket(
	            purchaseRequest.getFrom(),
	            purchaseRequest.getTo(),
	            user,
	            purchaseRequest.getPrice()
	    );
    	 // If seat number is not provided, assign a seat between 1 and 50
        if (purchaseRequest.getSeatNum() == 0) {
            int availableSeat = findAvailableSeat();
            if (availableSeat == -1) {
                // Handle the case where no available seat is found
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
            newTicket.setSeatNum(availableSeat);
        }

        Ticket purchasedTicket = ticketRepository.save(newTicket);
        return ResponseEntity.ok(purchasedTicket);
    }

    private int findAvailableSeat() {
        for (int i = 1; i <= 50; i++) {
            if (!ticketRepository.existsBySeatNum(i)) {
                return i;
            }
        }
        return -1; // No available seat found
    }
    
	public Ticket getTicketByUserId(Long userId) {
		Optional<Ticket> optionalTicket = ticketRepository.findById(userId);
        return optionalTicket.orElse(null);
	}
	public List<Ticket> getTicketsBySection(String section) {
		List<Ticket> ticketsBySection = ticketRepository.findByUser_AllocatedSection(section);

        return ticketsBySection;
	}
	public boolean removeUserFromTrain(Long userId) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(userId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();
            ticketRepository.delete(ticket);
            return true;
        }

        return false;
    }
	
	@Transactional
    public ResponseEntity<Ticket> modifyUserSeat(Long userId, int newSeatNum) {
        Optional<Ticket> optionalTicket = ticketRepository.findById(userId);

        if (optionalTicket.isPresent()) {
            Ticket ticket = optionalTicket.get();

            // Check if the new seat is available
            if (!isSeatOccupied(newSeatNum)) {
                ticket.setSeatNum(newSeatNum);
                Ticket modifiedTicket = ticketRepository.save(ticket);
                return ResponseEntity.ok(modifiedTicket);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }

    private boolean isSeatOccupied(int seatNum) {
        return ticketRepository.existsBySeatNum(seatNum);
    }
}
