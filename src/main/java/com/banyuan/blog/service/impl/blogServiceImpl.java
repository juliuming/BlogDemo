package com.banyuan.blog.service.impl;

import com.banyuan.blog.mapper.BlogMapper;
import com.banyuan.blog.model.Blog;
import com.banyuan.blog.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class blogServiceImpl implements BlogService {
    @Autowired
    BlogMapper blogMapper;

    @Override
    public List<Blog> showUserBlogs(String username) {
        return blogMapper.selectBlogByUserName(username);
    }
}
