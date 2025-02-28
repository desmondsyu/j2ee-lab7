package com.onlinebanking.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.function.Function;

@Component
public class JwtTokenProvider {

    private final String SECRET_KEY = "661d73b73e3a65aa8a8f574cf807c497de9b5e54e7749dfb563ddeeb3d379c3dec3df2f9831b3d98d3609996751d133d79a789b9462633ae13ad62e1e14f73ee7741e603d4ff8aa8ce04efad8248251eff3c2be0124b57824337b43d7f1b5f73c271049b45df8e5d34e5cf9dfdf9ea9801f496abb68ae6eb321c989dd612e058c9d6aec463e510467e28dd6c588bf8d717cdb86bd29076723bf38e49066949cb0ba84c4dd2d51ca0c762f9624ea1fe06ab7b215181c5eaccdcc35a9eda4cf07141092204601fc4d429d01adc338138c6fb970a88c21bc8b93b71b308fa4eb25f64c02f5ece5f37995133b369ce0c61c7ffa37b011b6f901309594db71cc8612e";
    private final long EXPIRATION_TIME = 86400000; // 1 day

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claimsResolver.apply(claims);
    }
}
