package com.sck.helpdesk.controller;

import com.sck.helpdesk.security.CurrentUserUtility;
import com.sck.helpdesk.service.TicketService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/home")
public class HomeController {

    private final TicketService ticketService;

    public HomeController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping
    public String displayHome(Model model) {

        if(CurrentUserUtility.hasRole("ROLE_AGENT")) {
            model.addAttribute("tickets", ticketService.openAssignedCurrentUser());
        } else {
            model.addAttribute("tickets", ticketService.openCreatedCurrentUser());
        }


        return "home/index";
    }

}
