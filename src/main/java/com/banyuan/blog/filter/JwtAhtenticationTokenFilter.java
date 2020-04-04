package com.banyuan.blog.filter;

import com.banyuan.blog.dto.BlogUserDetails;
import com.banyuan.blog.model.User;
import com.banyuan.blog.utils.HttpUtils;
import com.banyuan.blog.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;

import javax.servlet.*;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAhtenticationTokenFilter extends HttpFilter {

    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private HttpUtils httpUtils;
    @Value("${jwt.tokenHeader}")
    private String tokenHeader;
    @Value("${jwt.tokenHead}")
    private String tokenHead;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException, ServletException {
        Cookie jwtCookie = httpUtils.getCookieByName(request, tokenHeader);
        String authHeader = null;
        if (jwtCookie != null) { 
            authHeader = jwtCookie.getValue();
        }
        
        if (authHeader != null && authHeader.startsWith(this.tokenHead)) {
            String authToken = authHeader.substring(this.tokenHead.length());// The part after "Bearer "
            String username = jwtUtils.getUserNameFromToken(authToken);
            String password = jwtUtils.getPasswordFromToken(authToken);
            if (username != null && password != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
                //if (jwtUtils.validateToken(authToken, userDetails)) {
                User user = new User();
                user.setUsername(username);
                user.setPassword(password);
                UserDetails userDetails = new BlogUserDetails(user);
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
                //}
            }
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
