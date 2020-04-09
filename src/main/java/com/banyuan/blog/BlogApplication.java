package com.banyuan.blog;

import com.banyuan.blog.mapper.BlogMapper;
import com.banyuan.blog.mapper.CommentMapper;
import com.banyuan.blog.mapper.UserMapper;
import com.banyuan.blog.model.Blog;
import com.banyuan.blog.model.Comment;
import com.banyuan.blog.model.User;
import com.banyuan.blog.service.EmailService;
import com.banyuan.blog.service.RedisService;
import com.banyuan.blog.utils.MyUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import java.util.List;


@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) throws MessagingException {
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        ApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        /*MyUtils myUtils = context.getBean(MyUtils.class);
        EmailService emailService = context.getBean(EmailService.class);
        String token = myUtils.generateVCode(6, true);
        String content =
                String.format("<p>尊敬的用户你好,感谢注册半圆blog," +
                        "请点击<a href=\"http://localhost:8081/register/active?username=%s&token=%s\">" +
                        "激活</a>您的账号","juliuming",token)
                //+ "尊敬的用户你好,感谢注册半圆blog,请点击<a href=\"http://localhost:8081/register/active?username="
                //+ "juliuming"
               // + "&token="
                //+ token + "\"/>"
                ;
        System.out.println(content);
        emailService.sendEmail("juliuming@163.com", "半圆博客激活邮件", content);*/
        //UserMapper userMapper = context.getBean(UserMapper.class);
        // BlogMapper blogMapper = context.getBean(BlogMapper.class);
        //CommentMapper commentMapper = context.getBean(CommentMapper.class);
        //JavaMailSender javaMailSender = context.getBean(JavaMailSender.class);
        // User user = userMapper.selectUserByUsername("Durant1988");
        // Blog blog = blogMapper.selectBlogById(1);
        //List<Comment> commentList = commentMapper.selectCommentByBlogId(1L);
        //System.out.println(commentList);
        //System.out.println(user);
        //System.out.println(blog);
    }
}
