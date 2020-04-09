package com.banyuan.blog.controller;

import com.banyuan.blog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;


@Controller
public class UserManageController {
    @Autowired
    UserService userService;

    @GetMapping("/login")
    String toLogin() {
        return "login.html";
    }

    @GetMapping("/create")
    String create() {
        return "create.html";
    }

    @GetMapping("/edit")
    String edit() {
        return "edit.html";
    }

    @GetMapping("/picView")
    String picView() {
        return "picview.html";
    }

    @GetMapping("/register/active")
    String active(@RequestParam String username, @RequestParam String token, HttpServletResponse response) {
        if (userService.active(username,token,response)){
            return "activeSuccess.html";
        }else {
            return "activeError.html";
        }
    }

    @GetMapping("/register")
    String active(){
        return "register.html";
    }

    @PostMapping("/register")
    String register(@RequestParam String email,
                    @RequestParam String username,
                    @RequestParam String password,
                    @RequestParam String nickName,
                    @RequestParam String gender,
                    @RequestParam int age,
                    @RequestParam String address,
                    @RequestParam String qq) throws MessagingException {
        String issuccess = userService.regist(username, password, nickName, gender, age, address,
                qq, email);
        if (issuccess.equals("success")) {
            return "toActive.html";
        } else {
            return "redirect:error";
        }
    }

    @GetMapping("upload")
    String upload() {
        return "upload.html";
    }
}
