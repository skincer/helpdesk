package com.sck.helpdesk.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/category")
public class CategoryController {

    @GetMapping
    public String displayIndex() {

        return "category/index";
    }

}
