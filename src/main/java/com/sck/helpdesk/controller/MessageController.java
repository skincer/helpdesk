package com.sck.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/message")
public class MessageController {

    @GetMapping
    public String displayIndex() {

        return "message/index";
    }

}
