package com.banyuan.blog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SearchController {
    @RequestMapping("/search")
    @ResponseBody
    String searchTitle(@RequestParam String keys){
        return "you are searching" + keys;
    }
}
