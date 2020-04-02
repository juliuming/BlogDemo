package com.banyuan.blog.controller;

import com.banyuan.blog.model.Blog;
import com.banyuan.blog.model.Comment;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.BlogService;
import com.banyuan.blog.service.CommentService;
import com.banyuan.blog.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

@Controller
public class BlogController {
    @Autowired
    BlogService blogService;

    @Autowired
    CommentService commentService;

    @Autowired
    UserService userService;

    @RequestMapping("/blog/{username}")
    String showBlogByUsername(
            @PathVariable(value = "username") String userName, Model model,
            @RequestParam Optional<Integer> pageNum,
            @RequestParam Optional<Integer> pageSize
    ) {

        PageHelper.startPage(pageNum.orElse(1), pageSize.orElse(10));
        List<Blog> userBlogList = blogService.showUserBlogs(userName);
        PageInfo<Blog> pageInfo = new PageInfo<>(userBlogList);
        List<Comment> commentList = commentService.showComments(userBlogList.get(0).getId());

        //临时的user,login功能完善后删掉代码
        User user = userBlogList.get(0).getUser();//delete when login completed

        model.addAttribute("blogs", pageInfo);
        model.addAttribute("comment", commentList);
        model.addAttribute("user", user);
        return "list.html";
    }

    @RequestMapping("/blog/create")
    String createBlog() {
        return "create.html";
    }

    @RequestMapping(value = "/blog/create", method = RequestMethod.POST)
    String createAndSaveBlog(
            @RequestParam String title,
            @RequestParam String tag,
            @RequestParam String content,
            HttpSession httpSession
    ) {
        //先存一个user进session,loin功能完成后改掉
        User user = userService.getUserByUsername("Durant");
        httpSession.setAttribute("user", user);

        return "redirect:list.html";
    }
}
