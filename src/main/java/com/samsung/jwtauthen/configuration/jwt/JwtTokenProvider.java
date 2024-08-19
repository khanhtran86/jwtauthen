package com.samsung.jwtauthen.configuration.jwt;

import com.samsung.jwtauthen.configuration.CustomUserDetails;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Date;

@Component
/*Đây là class giúp extract thông tin từ jwt hoặc generate thông tin ra jwt*/
public class JwtTokenProvider {
    private String JWT_SECRET = "2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D2D4A614E645267556B58703273357638792F423F4428472B4B6250655368566D";
    private final Long JWT_EXPIRATION = 604800000L;
    public String generateToken(CustomUserDetails userDetails)
    {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime()+ JWT_EXPIRATION);
        //Tao json web token
        return Jwts.builder()
                .setSubject(userDetails.getUser().getUsername())
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS512, JWT_SECRET)
                .compact();
    }

    public boolean validateToken(String authToken)
    {
        try {
            Jwts.parser().setSigningKey(JWT_SECRET).parseClaimsJws(authToken);
            return true;
        }
        catch (Exception ex)
        {
            throw ex;
        }
    }

    public String getUserNameFromJWT(String token)
    {
        Claims claims = Jwts.parser()
                .setSigningKey(JWT_SECRET)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject(); //return username
    }
}
