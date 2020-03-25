package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.domain.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TicketRepository extends JpaRepository<TicketEntity, Long> {

    List<TicketEntity> findAllByUserAssignedAndStatus(UserEntity userAssigned, TicketEntity.TicketStatus status);
}
