package com.sck.helpdesk.controller;

import com.sck.helpdesk.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app/category")
public class CategoryController {

    private final CategoryRepository categoryRepository;

    public CategoryController(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping
    public String displayIndex(Model model) {

        model.addAttribute("categories", categoryRepository.findAll());

        return "category/index";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/{id}")
    public String displayDetail(Model model, @PathVariable Long id) {

        model.addAttribute("category", categoryRepository.getOne(id));

        return "category/detail";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/edit/{id}")
    public String displayEdit(Model model, @PathVariable Long id) {

        model.addAttribute("category", categoryRepository.getOne(id));

        return "category/edit";
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/create")
    public String displayCreate(Model model) {

        return "category/create";
    }

}
