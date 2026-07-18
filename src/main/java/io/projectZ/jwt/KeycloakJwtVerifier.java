package io.projectZ.jwt;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 6/23/2026 - 10:18 AM
*/

import com.auth0.jwk.*;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.projectZ.config.Config;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.MalformedURLException;
import java.net.URL;
import java.security.interfaces.RSAPublicKey;

public class KeycloakJwtVerifier implements JwtVerifier{
    private final JwkProvider jwkProvider;
    private final String issuer;
    private final Logger logger = LogManager.getLogger(KeycloakJwtVerifier.class);

    public KeycloakJwtVerifier() {
        Config config = new Config();
        URL jwksUrl = null;
        try {
            jwksUrl = new URL(config.props.getProperty(Config.JWK_URL_STRING ));
        } catch (MalformedURLException e) {
            throw new RuntimeException(e);
        }
        this.issuer = config.props.getProperty(Config.JWT_ISSUER_STRING);
        this.jwkProvider = new JwkProviderBuilder(jwksUrl)
                .cached(true)
                .build();
        logger.info("jwkProvider created");
    }

    @Override
    public DecodedJWT verify(String token) throws JwkException {
        DecodedJWT jwt = JWT.decode(token);
        Jwk jwk = jwkProvider.get(jwt.getKeyId());
        Algorithm algorithm = Algorithm.RSA256((RSAPublicKey) jwk.getPublicKey(), null);
        logger.info("verifying for token {} ",token);
        return JWT.require(algorithm).withIssuer(issuer).build().verify(token);
    }
}

