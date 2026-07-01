package io.amirHFF.projectz.plugin;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 6/23/2026 - 1:28 AM
*/

import com.auth0.jwt.interfaces.DecodedJWT;
import io.amirHFF.projectz.jwt.JwtVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jivesoftware.openfire.auth.AuthProvider;
import org.jivesoftware.openfire.auth.ConnectionException;
import org.jivesoftware.openfire.auth.InternalUnauthenticatedException;
import org.jivesoftware.openfire.auth.UnauthorizedException;
import org.jivesoftware.openfire.user.UserNotFoundException;

public class JwtAuthProvider implements AuthProvider {
    private final Logger logger = LogManager.getLogger(JwtAuthProvider.class);
    private JwtVerifier jwtVerifier;

    public JwtAuthProvider(JwtVerifier jwtVerifier) {
        this.jwtVerifier = jwtVerifier;
    }

    @Override
    public void authenticate(String username, String password) throws UnauthorizedException, ConnectionException, InternalUnauthenticatedException {
       try {
           DecodedJWT jwt = jwtVerifier.verify(password);
           String user = jwt.getClaim("preferred_username").asString();
           if (!user.equals(username)) {
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
        return null;
    }

    @Override
    public void setPassword(String s, String s1) throws UserNotFoundException, UnsupportedOperationException {

        logger.error("unsupported operation : we do not support password in chat authentication");

    }

    @Override
    public boolean supportsPasswordRetrieval() {
        logger.error("unsupported operation : we do not support password in chat authentication");

        return false;
    }

    @Override
    public boolean isScramSupported() {
        logger.error("unsupported operation : we do not support password in chat authentication");

        return false;
    }

    @Override
    public String getSalt(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        return null;
    }

    @Override
    public int getIterations(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        return 0;
    }

    @Override
    public String getServerKey(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        return null;
    }

    @Override
    public String getStoredKey(String s) throws UnsupportedOperationException, UserNotFoundException {
        logger.error("unsupported operation : we do not support password in chat authentication");

        return null;
    }
}

