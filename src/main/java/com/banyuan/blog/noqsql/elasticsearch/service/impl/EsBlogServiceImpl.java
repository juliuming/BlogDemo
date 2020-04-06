package com.banyuan.blog.noqsql.elasticsearch.service.impl;

import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import com.banyuan.blog.noqsql.elasticsearch.repositories.BlogRepository;
import com.banyuan.blog.noqsql.elasticsearch.service.EsBlogService;
import com.banyuan.blog.service.BlogService;
import com.github.pagehelper.Page;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Iterator;
import java.util.List;

public class EsBlogServiceImpl implements EsBlogService {


    @Autowired
    BlogService blogService;

    @Autowired
    BlogRepository blogRepository;

    //此方法有风险,需要认证权限,要进行角色权限认证
    @Override
    public int importAll() {
        List<EsBlog> esBlogList = blogService.getAllEsBlogs();
        Iterable<EsBlog> esBlogIterable = blogRepository.saveAll(esBlogList);
        Iterator<EsBlog> iterator = esBlogIterable.iterator();
        int result = 0;
        while (iterator.hasNext()) {
            result++;
            iterator.next();
        }
        return result;
    }


    //未完待续
    @Override
    public void delete(Long id) {

    }

    @Override
    public EsBlog create(List<Long> ids) {
        return null;
    }

    @Override
    public Page<EsBlog> search(String keyword, Integer pageNum, Integer pageSize) {
        return null;
    }
}
