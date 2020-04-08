package com.banyuan.blog.noqsql.elasticsearch.service;

import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EsBlogService {
    int importAll();

    void delete(Long id);

    EsBlog create(Long ids);

    List<EsBlog> create(List<Long> ids);

    public void delete(List<Long> ids);

    Page<EsBlog> search(String keyword, Integer pageNum, Integer pageSize);

}
