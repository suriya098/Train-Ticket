package com.example.trainTicket.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.trainTicket.entity.Ticket;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    
	List<Ticket> findByUser_AllocatedSection(String section);

	boolean existsBySeatNum(int seatNum);
}
