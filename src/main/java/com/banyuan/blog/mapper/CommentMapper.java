package com.banyuan.blog.mapper;

import com.banyuan.blog.model.Comment;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CommentMapper {
    List<Comment> selectCommentByBlogId(int blogId);
    List<Comment> selectCommentByUserId(int userId);
}
