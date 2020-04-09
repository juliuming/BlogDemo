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

    @GetMapping("/create")
    String create(){
        return "create.html";
    }

    @GetMapping("/edit")
    String edit(){
        return "edit.html";
    }

    @GetMapping("/picView")
    String picView(){
        return "picview.html";
    }

    @GetMapping("/register")
    String register(@RequestParam String email,
                    @RequestParam String name,
                    @RequestParam String password,
                    @RequestParam String nickName,
                    @RequestParam String gender,
                    @RequestParam int age,
                    @RequestParam String address,
                    @RequestParam String qq){

        return "register.html";
    }

    @GetMapping("upload")
    String upload(){
        return "upload.html";
    }
}
