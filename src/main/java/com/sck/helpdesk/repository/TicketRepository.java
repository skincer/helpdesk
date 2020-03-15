package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {
}
