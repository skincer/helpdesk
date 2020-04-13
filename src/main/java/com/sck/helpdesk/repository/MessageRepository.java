package com.sck.helpdesk.repository;

import com.sck.helpdesk.domain.MessageEntity;
import com.sck.helpdesk.domain.TicketEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<MessageEntity, Long> {

    List<MessageEntity> findAllByTicket(TicketEntity ticket);

    List<MessageEntity> findFirst10ByContentContainingIgnoreCaseOrderByIdDesc(String content);
}
