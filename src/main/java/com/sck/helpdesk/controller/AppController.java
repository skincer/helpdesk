package com.sck.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class AppController {

    @GetMapping
    public String homeRedirect() {
        return "redirect:/app/home";
    }

    @GetMapping(value = "/login")
    public String login() {
        return "login";
    }

}
