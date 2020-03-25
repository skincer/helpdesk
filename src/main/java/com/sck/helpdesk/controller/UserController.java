package com.sck.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/user")
public class UserController {

    @GetMapping
    public String displayIndex() {

        return "user/index";
    }

}
