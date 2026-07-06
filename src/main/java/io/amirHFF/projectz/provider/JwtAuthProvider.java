package io.amirHFF.projectz.provider;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 6/23/2026 - 1:28 AM
*/

import com.auth0.jwt.interfaces.DecodedJWT;
import io.amirHFF.projectz.jwt.JwtVerifier;
import io.amirHFF.projectz.jwt.KeycloakJwtVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.openfire.auth.*;
import org.jivesoftware.openfire.user.UserNotFoundException;

public class JwtAuthProvider implements AuthProvider {
    private final Logger logger = LogManager.getLogger(JwtAuthProvider.class);
    private final JwtVerifier jwtVerifier = new KeycloakJwtVerifier();


    @Override
    public void authenticate(String username, String password) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
        logger.info("custom JWT authentication start for : "+username);
       try {
           DecodedJWT jwt = jwtVerifier.verify(password);
           logger.info(username + "authorized");
           String user = jwt.getClaim("preferred_username").asString();
           if (!username.equals(user)) {
               logger.error("token exist ,username mismatch");
               throw new UnauthorizedException("user is incorrect");
           }
       }catch (Exception exception){
           throw new UnauthorizedException("user not authorized" ,exception);
       }


    }

    @Override
    public String getPassword(String s) throws UserNotFoundException, UnsupportedOperationException {
        logger.error("unsupported operation : we do not support password in chat authentication");
        throw new UnsupportedOperationException("getPassword ... , we do not support regular authentication");
    }

    @Override
    public void setPassword(String s, String s1) throws UserNotFoundException, UnsupportedOperationException {

        throw new UnsupportedOperationException("setPassword ... , we do not support regular authentication");

    }

    @Override
    public boolean supportsPasswordRetrieval() {

        return false;
    }

    @Override
    public boolean isScramSupported() {

        return false;
    }

    @Override
    public String getSalt(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        throw new UnsupportedOperationException("getSalt ... , we do not support regular authentication");
    }

    @Override
    public int getIterations(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        throw new UnsupportedOperationException("getIterations ... , we do not support regular authentication");

    }

    @Override
    public String getServerKey(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        throw new UnsupportedOperationException("getServerKey ... , we do not support regular authentication");
    }

    @Override
    public String getStoredKey(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        throw new UnsupportedOperationException("getStoredKey ... , we do not support regular authentication");
    }
}

