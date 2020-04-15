package com.sck.helpdesk.controller;

import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/app/report")
public class ReportController {

    private final UserRepository userRepository;
    private final TicketRepository ticketRepository;

    public ReportController(UserRepository userRepository, TicketRepository ticketRepository) {
        this.userRepository = userRepository;
        this.ticketRepository = ticketRepository;
    }

    @GetMapping
    public String displayIndex(Model model) {

        return "report/index";
    }

    @GetMapping("/agentsByTickets")
    public String displayAgentsByTickets(Model model) {

        List<UserEntity> agents = userRepository.findAllByType(UserEntity.UserType.AGENT);

        model.addAttribute("agents", agents);

        return "report/agentsByTickets";
    }

    @GetMapping("/customersByTickets")
    public String displayCustomersByTickets(Model model) {

        model.addAttribute("customers", userRepository.findAllByType(UserEntity.UserType.CUSTOMER));

        return "report/customersByTickets";
    }

}
