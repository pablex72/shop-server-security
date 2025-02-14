package com.ecommerce.shop.service;

import com.ecommerce.shop.entity.User;
import io.jsonwebtoken.Header;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${security.jwt.expiration-minutes}")
    private long EXPIRATION_MINUTES;
    @Value("${security.jwt.expiration-key}")
    private String SECRET_KEY;

    public String generateToken(User user, Map<String,Object> generateExtraClaims) {
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date( issuedAt.getTime()+(EXPIRATION_MINUTES));

        return Jwts.builder()
                .setClaims(generateExtraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key generateKey(){

        byte[] secretAsBytes = Decoders.BASE64.decode(SECRET_KEY);
        System.out.println("mi clave es " + new String(secretAsBytes));
        return Keys.hmacShaKeyFor(secretAsBytes);
    }
}
