package com.sck.helpdesk.controller;

import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.repository.MessageRepository;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final TicketRepository ticketRepository;
    private final MessageRepository messageRepository;

    public TicketController(TicketService ticketService, TicketRepository ticketRepository, MessageRepository messageRepository) {
        this.ticketService = ticketService;
        this.ticketRepository = ticketRepository;
        this.messageRepository = messageRepository;
    }

    @GetMapping
    public String displayIndex(Model model) {

        model.addAttribute("tickets", ticketRepository.findAllByStatus(TicketEntity.TicketStatus.OPEN));

        return "ticket/index";
    }

    @GetMapping("/{id}")
    public String displayDetail(Model model, @PathVariable Long id) {

        TicketEntity ticket = ticketRepository.getOne(id);
        if(ticket == null) return "redirect:/app/home";

        model.addAttribute("ticket", ticket);
        model.addAttribute("messages", messageRepository.findAllByTicket(ticket));

        return "ticket/detail";
    }

    @GetMapping("/edit/{id}")
    public String displayEdit(Model model, @PathVariable Long id) {

        TicketEntity ticket = ticketRepository.getOne(id);
        if(ticket == null) return "redirect:/app/home";

        model.addAttribute("ticket", ticket);

        return "ticket/edit";
    }

    @GetMapping("/create")
    public String displayCreate(Model model) {

        return "ticket/create";
    }

}
