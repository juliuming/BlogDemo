package com.banyuan.blog.service.impl;

import com.banyuan.blog.mapper.CommentMapper;
import com.banyuan.blog.model.Comment;
import com.banyuan.blog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public List<Comment> showComments(int blogId) {
        return commentMapper.selectCommentByBlogId(blogId);
    }
}
