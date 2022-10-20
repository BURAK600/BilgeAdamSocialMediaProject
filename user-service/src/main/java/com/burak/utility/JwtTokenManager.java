package com.burak.utility;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.burak.exception.ErrorType;
import com.burak.exception.UserProfileServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;


@Service
public class JwtTokenManager {

    @Value("${myjwt.secretKey}")
    private String secretKey;

    @Value("${myjwt.audience}")
    private String audience;

    @Value("${myjwt.issuer}")
    private String issuer;


    public String createToken(Long authId){
        String token = null;
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            token = JWT.create()
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .withIssuedAt(new Date())
                    .withExpiresAt(new Date(System.currentTimeMillis()+(1000*60)))
                    .withClaim("authId", authId)
                    .sign(algorithm);
            return token;

        }catch (Exception e){
            return null;
        }
    }

    public Optional<Long> getByIdFromToken(String token){
        try{
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            JWTVerifier jwtVerifier = JWT.require(algorithm)
                    .withAudience(audience)
                    .withIssuer(issuer)
                    .build();

            DecodedJWT decodedToken = jwtVerifier.verify(token);

            if(decodedToken==null) throw new UserProfileServiceException(ErrorType.GECERSIZ_TOKEN);
            Long authId = decodedToken.getClaim("authId").asLong();
            return Optional.of(authId);

        }catch (Exception e){
            throw new UserProfileServiceException(ErrorType.GECERSIZ_TOKEN);
        }
    }



}
