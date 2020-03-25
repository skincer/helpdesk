package com.sck.helpdesk.service;

import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.repository.UserRepository;
import com.sck.helpdesk.security.CurrentUserUtility;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;

    public TicketService(TicketRepository ticketRepository, UserRepository userRepository) {
        this.ticketRepository = ticketRepository;
        this.userRepository = userRepository;
    }

    public List<TicketEntity> openAssignedCurrentUser() {

        UserEntity user = userRepository.findByUsername(CurrentUserUtility.username());

        return ticketRepository.findAllByUserAssignedAndStatus(
                user, TicketEntity.TicketStatus.OPEN
        );
    }
}
