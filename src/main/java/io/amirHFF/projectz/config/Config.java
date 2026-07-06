package io.amirHFF.projectz.config;
/*
  Project : openfire-auth-plugin
  Author  : AmirHFF
  Created : 7/2/2026 - 10:36 PM
*/

import org.jivesoftware.util.JiveGlobals;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;

public class Config {
    public static String JWK_URL_STRING= "jwt.jwksUrl";
    public static String JWT_ISSUER_STRING= "jwt.issuer";
    public  Properties props = new Properties();

    public Config() {
//            props.put(JWK_URL_STRING, JiveGlobals.getProperty(JWK_URL_STRING));
            props.put(JWK_URL_STRING, "http://130.185.121.173:8081/realms/project-z/protocol/openid-connect/certs");
//            props.put(JWT_ISSUER_STRING, JiveGlobals.getProperty(JWK_URL_STRING));
            props.put(JWT_ISSUER_STRING, "http://130.185.121.173:8081/realms/project-z");
    }

}

