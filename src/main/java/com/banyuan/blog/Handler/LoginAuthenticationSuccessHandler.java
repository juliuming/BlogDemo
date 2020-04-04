package com.banyuan.blog.Handler;

import com.banyuan.blog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class LoginAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Autowired
    JwtUtils jwtUtils;


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        Object userDetails = authentication.getPrincipal();
        if (userDetails instanceof UserDetails) {
            Cookie jwtCookie = new Cookie(tokenHeader,tokenHead+jwtUtils.generateToken((UserDetails) userDetails));
            jwtCookie.setMaxAge(14*24*3600);
            response.addCookie(jwtCookie);
        }
        super.onAuthenticationSuccess(request,response,authentication);
    }
}
