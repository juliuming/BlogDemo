package com.banyuan.blog.noqsql.elasticsearch.service;

import com.banyuan.blog.model.Blog;
import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import com.github.pagehelper.Page;

import java.util.List;

public interface EsBlogService {
    int importAll();

    void delete(Long id);

    EsBlog create(List<Long> ids);

    Page<EsBlog> search(String keyword, Integer pageNum, Integer pageSize);

}
