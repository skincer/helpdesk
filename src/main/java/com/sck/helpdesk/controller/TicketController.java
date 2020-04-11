package com.sck.helpdesk.controller;

import com.sck.helpdesk.domain.MessageEntity;
import com.sck.helpdesk.domain.TicketEntity;
import com.sck.helpdesk.dto.MessageCreateForm;
import com.sck.helpdesk.dto.TicketCreateForm;
import com.sck.helpdesk.dto.TicketResolveForm;
import com.sck.helpdesk.repository.CategoryRepository;
import com.sck.helpdesk.repository.MessageRepository;
import com.sck.helpdesk.repository.TicketRepository;
import com.sck.helpdesk.security.CurrentUserUtility;
import com.sck.helpdesk.service.TicketService;
import com.sck.helpdesk.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/app/ticket")
public class TicketController {

    private final TicketService ticketService;
    private final UserService userService;
    private final TicketRepository ticketRepository;
    private final MessageRepository messageRepository;
    private final CategoryRepository categoryRepository;

    public TicketController(TicketService ticketService, UserService userService, TicketRepository ticketRepository, MessageRepository messageRepository, CategoryRepository categoryRepository) {
        this.ticketService = ticketService;
        this.userService = userService;
        this.ticketRepository = ticketRepository;
        this.messageRepository = messageRepository;
        this.categoryRepository = categoryRepository;
    }


    @GetMapping
    public String displayIndex(Model model) {

        model.addAttribute("tickets", ticketRepository.findAllByStatus(TicketEntity.TicketStatus.OPEN));

        return "ticket/index";
    }

    @GetMapping("/{id}")
    public String displayDetail(Model model, @PathVariable Long id, MessageCreateForm messageCreateForm, TicketResolveForm ticketResolveForm) {

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
    public String displayCreate(Model model, TicketCreateForm ticketCreateForm) {

        model.addAttribute("categories", categoryRepository.findAll());

        return "ticket/create";
    }

    @PostMapping("/create")
    public String handleCreate(Model model, @Valid TicketCreateForm ticketCreateForm) {

        TicketEntity ticketEntity = new TicketEntity(
                ticketCreateForm.getTitle(),
                ticketCreateForm.getContent(),
                CurrentUserUtility.userEntity(),
                userService.retrieveAvailableAgent(),
                categoryRepository.getOne(ticketCreateForm.getCategoryId())
        );

        TicketEntity createdTicket = ticketRepository.save(ticketEntity);

        return "redirect:/app/ticket/" + createdTicket.getId();
    }

    @PostMapping("/{id}/createMessage")
    public String handleCreateMessage(Model model, @Valid MessageCreateForm messageCreateForm, @PathVariable Long id) {

        MessageEntity messageEntity = new MessageEntity(
                messageCreateForm.getContent(),
                ticketRepository.getOne(id),
                CurrentUserUtility.userEntity()
                );

        messageRepository.save(messageEntity);

        return "redirect:/app/ticket/" + id;
    }

    @PostMapping("/{id}/resolve")
    public String handleResolve(Model model, @Valid TicketResolveForm ticketResolveForm, @PathVariable Long id) {

        TicketEntity ticketEntity = ticketRepository.getOne(id);
        ticketEntity.setResolution(ticketResolveForm.getResolution());
        ticketEntity.setStatus(TicketEntity.TicketStatus.CLOSED);

        ticketRepository.save(ticketEntity);

        return "redirect:/app/ticket/" + id;
    }

    @PostMapping("/{id}/delete")
    public String handleDelete(Model model, @PathVariable Long id) {

        ticketRepository.deleteById(id);

        return "redirect:/app/ticket";
    }

}
