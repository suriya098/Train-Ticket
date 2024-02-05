package com.example.trainTicket.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.trainTicket.entity.Ticket;
import com.example.trainTicket.entity.User;
import com.example.trainTicket.request.PurchaseRequest;
import com.example.trainTicket.service.TicketService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {
	
	 private final TicketService ticketService;

	 @Autowired
	 public TicketController(TicketService ticketService) {
	 this.ticketService = ticketService;
	 }
	
	 @PostMapping("/purchase")
	    public ResponseEntity<?> purchaseTicket(@Valid @RequestBody PurchaseRequest purchaseRequest, BindingResult bindingResult) {
		 if (bindingResult.hasErrors()) {
			 return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(createValidationErrorResponse(bindingResult));
		 }
		 return ticketService.purchaseTicket(purchaseRequest);
	 }
	 
	 @GetMapping("/receipt/{userId}")
	    public ResponseEntity<Ticket> getReceiptDetails(@PathVariable Long userId) {
	        Ticket ticket = ticketService.getTicketByUserId(userId);

	        if (ticket == null) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        return ResponseEntity.ok(ticket);
	    }
	 
	 @GetMapping("/users/{section}")
	    public ResponseEntity<List<Ticket>> getUsersBySection(@PathVariable String section) {
	        List<Ticket> ticketsBySection = ticketService.getTicketsBySection(section);

	        if (ticketsBySection.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
	        }

	        return ResponseEntity.ok(ticketsBySection);
	    }
	 
	 @DeleteMapping("/removeUser/{userId}")
	    public ResponseEntity<Void> removeUserFromTrain(@PathVariable Long userId) {
	        boolean removed = ticketService.removeUserFromTrain(userId);

	        if (!removed) {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }

	        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
	    }
	 
	 @PutMapping("/modifySeat/{userId}")
	    public ResponseEntity<Ticket> modifyUserSeat(
	            @PathVariable Long userId,
	            @RequestBody Map<String, Integer> requestBody) {

	        Integer newSeatNum = requestBody.get("seatNum");

	        if (newSeatNum == null) {
	            return ResponseEntity.badRequest().build();
	        }

	        return ticketService.modifyUserSeat(userId, newSeatNum);
	    }

	 private Map<String, String> createValidationErrorResponse(BindingResult bindingResult) {
	        Map<String, String> errors = new HashMap<>();
	        for (FieldError error : bindingResult.getFieldErrors()) {
	            errors.put(error.getField(), error.getDefaultMessage());
	        }
	        return errors;
	    }
}
