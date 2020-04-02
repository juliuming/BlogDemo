package com.banyuan.blog.config;

import org.springframework.beans.factory.annotation.Value;

public class UrlPermitListConfig {
    @Value("${Security.permittedUrls}")
    private static String [] permittedUrls;

    public static String[] getPermittedUrls(){
        return permittedUrls;
    }
}
