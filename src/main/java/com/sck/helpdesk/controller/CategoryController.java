package com.sck.helpdesk.controller;

import com.sck.helpdesk.domain.CategoryEntity;
import com.sck.helpdesk.dto.CategoryCreateForm;
import com.sck.helpdesk.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

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
    @GetMapping("/{id}/edit")
    public String displayEdit(Model model, @PathVariable Long id, CategoryCreateForm categoryCreateForm) {

        CategoryEntity categoryEntity = categoryRepository.getOne(id);

        model.addAttribute("category", categoryEntity);

        categoryCreateForm.setName(categoryEntity.getName());

        return "category/edit";
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/{id}/edit")
    public String handleEdit(Model model, @PathVariable Long id, @Valid CategoryCreateForm categoryCreateForm, BindingResult bindingResult) {

        CategoryEntity categoryEntity = categoryRepository.getOne(id);
        categoryEntity.setName(categoryCreateForm.getName());
        try {
        categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            bindingResult.rejectValue("name", "error.name", "Must be unique");
            model.addAttribute("error", "Can't update this category, check errors below.");
            model.addAttribute("category", categoryEntity);
            return "category/edit";
        }
        return "redirect:/app/category/"+id;
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/create")
    public String displayCreate(Model model, CategoryCreateForm categoryCreateForm) {

        return "category/create";
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/create")
    public String handleCreate(Model model, @Valid CategoryCreateForm categoryCreateForm, BindingResult bindingResult) {

        CategoryEntity categoryEntity = new CategoryEntity(categoryCreateForm.getName());
        CategoryEntity createdCategory;
        try {
            createdCategory = categoryRepository.save(categoryEntity);
        } catch (Exception e) {
            bindingResult.rejectValue("name", "error.name", "Must be unique");
            model.addAttribute("error", "Can't create this category, check errors below.");
            return "category/create";
        }

        return "redirect:/app/category/" + createdCategory.getId();
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/{id}/delete")
    public String handleDelete(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            categoryRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Can't delete this category, make sure it isn't in use!");
            return "redirect:/app/category/"+id;
        }

        return "redirect:/app/category";
    }

}
