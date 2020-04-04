package com.banyuan.blog.utils;

import org.springframework.stereotype.Component;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

@Component
public class HttpUtils {
    public Cookie getCookieByName(HttpServletRequest request, String name) {
        if (request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals(name)) {
                    return cookie;
                }
            }
        }
        return null;
    }
}
