package com.banyuan.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BlogController {
    @RequestMapping("/blog/{username}")
    @ResponseBody
    String showBlogByUsername(@PathVariable(value = "username")String userName){
        return "you are request" + userName + "blogs";
    }
}
