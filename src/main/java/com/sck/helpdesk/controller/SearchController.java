package com.sck.helpdesk.controller;

import com.sck.helpdesk.security.CurrentUserUtility;
import com.sck.helpdesk.service.SearchService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/app/search")
public class SearchController {

    private final SearchService searchService;

    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @PostMapping
    public String handleSearch(Model model, @RequestParam String query) {

        model.addAttribute("results", searchService.performSearch(query));
        model.addAttribute("query", query);

        return "search/result";
    }

}
