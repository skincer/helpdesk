package com.sck.helpdesk.controller;

import com.sck.helpdesk.domain.UserEntity;
import com.sck.helpdesk.dto.UserCreateForm;
import com.sck.helpdesk.dto.UserEditForm;
import com.sck.helpdesk.repository.UserRepository;
import com.sck.helpdesk.service.UserService;
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
@RequestMapping("/app/user")
public class UserController {

    private final UserService userService;
    private final UserRepository userRepository;

    public UserController(UserService userService, UserRepository userRepository) {
        this.userService = userService;
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
    @GetMapping("/{id}/edit")
    public String displayEdit(Model model, @PathVariable Long id, UserEditForm userEditForm) {

        UserEntity userEntity = userRepository.getOne(id);

        model.addAttribute("user", userEntity);

        userEditForm.setType(userEntity.getType());
        userEditForm.setUsername(userEntity.getUsername());

        return "user/edit";
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/{id}/edit")
    public String handleEdit(Model model, @PathVariable Long id, @Valid UserEditForm userEditForm, BindingResult bindingResult) {

        UserEntity editedUser;
        try {
            editedUser = userService.editUser(
                    id,
                    userEditForm.getUsername(),
                    userEditForm.getType()
            );
        } catch (Exception e) {
            UserEntity userEntity = userRepository.getOne(id);
            bindingResult.rejectValue("username", "error.username", "Must be unique");
            model.addAttribute("error", "Can't update this user, check errors below.");
            model.addAttribute("user", userEntity);
            return "user/edit";
        }

        return "redirect:/app/user/" + editedUser.getId();
    }

    @PreAuthorize("hasRole('AGENT')")
    @GetMapping("/create")
    public String displayCreate(Model model, UserCreateForm userCreateForm) {

        return "user/create";
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/create")
    public String handleCreate(Model model, @Valid UserCreateForm userCreateForm, BindingResult bindingResult) {

        UserEntity createdUser;
        try {
            createdUser = userService.createUser(
                    userCreateForm.getUsername(),
                    userCreateForm.getPassword(),
                    userCreateForm.getType()
            );
        } catch (Exception e) {
            bindingResult.rejectValue("username", "error.username", "Must be unique");
            model.addAttribute("error", "Can't create this user, check errors below.");
            return "user/create";
        }

        return "redirect:/app/user/" + createdUser.getId();
    }

    @PreAuthorize("hasRole('AGENT')")
    @PostMapping("/{id}/delete")
    public String handleDelete(Model model, @PathVariable Long id, RedirectAttributes redirectAttributes) {

        try {
            userRepository.deleteById(id);
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "Can't delete this user, make sure it isn't in use!");
            return "redirect:/app/user/"+id;
        }

        return "redirect:/app/user";
    }

}
