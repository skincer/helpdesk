package com.sck.helpdesk.controller;

import com.sck.helpdesk.repository.UserRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/user")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping
    public String displayIndex(Model model) {

        model.addAttribute("users", userRepository.findAll());

        return "user/index";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/{id}")
    public String displayDetail(Model model, @PathVariable Long id) {

        model.addAttribute("user", userRepository.getOne(id));

        return "user/detail";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/edit/{id}")
    public String displayEdit(Model model, @PathVariable Long id) {

        model.addAttribute("user", userRepository.getOne(id));

        return "user/edit";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/create")
    public String displayCreate(Model model) {

        return "user/create";
    }

}
