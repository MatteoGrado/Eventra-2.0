package de.grado.userservice.controller;

import de.grado.userservice.form.RegisterUserForm;
import de.grado.userservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController
{
    private final UserService userService;

    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @PostMapping("/login/user")
    public String loginCustomer(Model model, @RequestParam String email, @RequestParam String password, @RequestParam String role)
    {
        if ("visitor".equals(role)) {
            userService.loginAsCustomer(email, password);
            return "redirect:http://localhost:8083/customer/dashboard";
        } else if ("organizer".equals(role)) {
            userService.loginAsOrganizer(email, password);
            return "redirect:http://localhost:8083/organizer/dashboard";
        }

        throw new IllegalArgumentException("Ungültige Rolle");
    }

    @GetMapping("/register")
    public String register(Model model)
    {
        model.addAttribute("registerForm", new RegisterUserForm());
        return "register";
    }

    @PostMapping("/register")
    public String registerCustomer(Model model, @ModelAttribute RegisterUserForm registerUserForm)
    {
        userService.register(registerUserForm);
        return "redirect:/login";
    }
}
