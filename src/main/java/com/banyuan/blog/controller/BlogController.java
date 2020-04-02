package com.banyuan.blog.controller;

import com.banyuan.blog.model.Blog;
import com.banyuan.blog.model.Comment;
import com.banyuan.blog.service.BlogService;
import com.banyuan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @RequestMapping("/blog/{username}")
    String showBlogByUsername(@PathVariable(value = "username") String userName, Model model) {
        List<Blog> userBlogList = blogService.showUserBlogs(userName);
        List<Comment> commentList = commentService.showComments(userBlogList.get(0).getId());
        model.addAttribute("blog", userBlogList.get(0));
        model.addAttribute("comment",commentList);
        return "list.html";
    }
}
