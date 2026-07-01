package io.amirHFF.projectz.jwt;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 6/23/2026 - 10:18 AM
*/

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.jivesoftware.util.JiveGlobals;

import java.security.interfaces.RSAPublicKey;

public class KeycloakJwtVerifier implements JwtVerifier{
    private final JwkProvider jwkProvider;
    private final String issuer;

    public KeycloakJwtVerifier() {
        String jwksUrl = JiveGlobals.getProperty("jwt.auth.jwks.url",
                "https:///realms/myrealm/protocol/openid-connect/certs");

        this.issuer = JiveGlobals.getProperty("jwt.auth.issuer",
                "https://keycloak.example.com/realms/myrealm");

        this.jwkProvider = new JwkProviderBuilder(jwksUrl)
                .cached(true)           // caching خودکار
                .build();
    }

    @Override
    public DecodedJWT verify(String token) throws JwkException {
        DecodedJWT jwt = JWT.decode(token);
        Jwk jwk = jwkProvider.get(jwt.getKeyId());
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);

        return JWT.require(algorithm).withIssuer(issuer).build().verify(token);
    }
}

