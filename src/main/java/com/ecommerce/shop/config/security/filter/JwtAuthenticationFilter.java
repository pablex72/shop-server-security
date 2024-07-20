package com.ecommerce.shop.config.security.filter;

import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.UserRepository;
import com.ecommerce.shop.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        //1. obtener el header que tiene el jwt
        String authHeader = request.getHeader("Authorization"); //Bearer jwt

        if(authHeader == null || !authHeader.startsWith("Bearer ")){
            filterChain.doFilter(request,response);
            return;
        }
        // 2. obtener jwt desde header
        String jwt = authHeader.split(" ")[1];
        //3. obtener subjetc/username desde el jwt
        String username = jwtService.extractUsername(jwt);

        //4. setear un objeto authentication dentro del securityContext
        User user = userRepository.findByUsername(username).get();
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                username, null, user.getAuthorities()
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
        //5. Ejecutar el resto de filtros
        filterChain.doFilter(request,response);
    }
}
