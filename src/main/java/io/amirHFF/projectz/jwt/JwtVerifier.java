package io.amirHFF.projectz.jwt;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 6/23/2026 - 9:32 AM
*/

import com.auth0.jwk.InvalidPublicKeyException;
import com.auth0.jwk.JwkException;
import com.auth0.jwt.interfaces.DecodedJWT;

public interface JwtVerifier {

    DecodedJWT verify(String token) throws JwkException;
}

