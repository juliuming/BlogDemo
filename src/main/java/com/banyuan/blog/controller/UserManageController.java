package com.banyuan.blog.controller;

import com.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;


@Controller
public class UserManageController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    String toLogin(){
        return "login.html";
    }

    @PostMapping("/login")
    Model loginSubmit(@RequestParam String username,
                 @RequestParam String password,
                 Model model,
                 HttpServletResponse response) {
        String token = userService.logIn(username, password);
        model.addAttribute("Jwtoken",token);
        return model;
    }
}
