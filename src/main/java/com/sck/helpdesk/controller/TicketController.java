package com.sck.helpdesk.controller;

import com.sck.helpdesk.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/ticket")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String displayIndex(Model model) {

        model.addAttribute("tickets", ticketService.openAssignedCurrentUser());

        return "ticket/index";
    }
}
