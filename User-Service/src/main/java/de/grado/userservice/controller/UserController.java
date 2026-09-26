package de.grado.userservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
public class UserController
{
    @GetMapping("/login")
    public String login(Model model)
    {
        return "login";
    }

    @PostMapping("/login/user")
    public String loginCustomer(Model model, @RequestParam String username, @RequestParam String password, @RequestParam String role)
    {
        if ("visitor".equals(role)) {
            
        } else if ("organizer".equals(role)) {
            
        }

        return "userLogin";
    }
}
