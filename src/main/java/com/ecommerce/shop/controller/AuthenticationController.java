package com.ecommerce.shop.controller;

import com.ecommerce.shop.dto.AuthenticationRequest;
import com.ecommerce.shop.dto.AuthenticationResponse;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> login(
            @RequestBody @Valid AuthenticationRequest authenticationRequest){
        return null;
    }

    @GetMapping("/public-access")
    public String publicAccessEndpoint(){
        return "este endpoint es public";
    }
}
