package com.banyuan.blog.controller;

import com.banyuan.blog.noqsql.elasticsearch.document.EsBlog;
import com.banyuan.blog.noqsql.elasticsearch.service.impl.EsBlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Controller
public class SearchController {
    @Autowired
    EsBlogServiceImpl esBlogService;

    @RequestMapping("/esImportAll")
    @ResponseBody
    String importAll(){
        esBlogService.importAll();
        return "success";
    }

    @GetMapping("/search")
    @ResponseBody
    List searchTitle(@RequestParam String keyWords,
                       @RequestParam(defaultValue = "0") Integer pageNum,
                       @RequestParam(defaultValue = "10") Integer pageSize,
                       Model model){
        Page<EsBlog> page = esBlogService.search(keyWords,pageNum,pageSize);
        List<EsBlog> esBlogs = new ArrayList<>();
        for (EsBlog esBlog : page) {
            esBlogs.add(esBlog);
        }
        model.addAttribute("SearchResult",esBlogs);
        return esBlogs;
    }
}
