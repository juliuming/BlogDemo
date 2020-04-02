package com.banyuan.blog.service;

import com.banyuan.blog.model.Blog;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

@Repository
public interface BlogService {
    List<Blog> showUserBlogs(String userName);
}
