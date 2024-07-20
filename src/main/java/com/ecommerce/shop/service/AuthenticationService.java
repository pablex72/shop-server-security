package com.ecommerce.shop.service;

import com.ecommerce.shop.dto.AuthenticationRequest;
import com.ecommerce.shop.dto.AuthenticationResponse;
import com.ecommerce.shop.entity.User;
import com.ecommerce.shop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class AuthenticationService {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtService jwtService;

    public AuthenticationResponse login(AuthenticationRequest authenticationRequest) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(), authenticationRequest.getPassword()
        );
        authenticationManager.authenticate(authenticationToken); //ya logueado se obtendra el user de bbdd

        User user = userRepository.findByUsername(authenticationRequest.getUsername()).get();
//                .orElseThrow(()-> new RuntimeException("user not found from Authentication Service"));
        //generar el dto del token
        String jwt =jwtService.generateToken(user,generateExtraClaims(user));
        return new AuthenticationResponse(jwt);
    }

    private Map<String,Object> generateExtraClaims(User user) {
        Map<String, Object> extraClaims = new HashMap<>();
        extraClaims.put("name", user.getName());
        extraClaims.put("role", user.getRole().name());
        extraClaims.put("permissions", user.getAuthorities());
        return extraClaims;
    }
}
