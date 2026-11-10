package org.ais.hr.fi.util;

/**
 * Created by IntelliJ IDEA.
 * Author: Murtaza Malik
 * Date: 11/1/2021
 * Time: 12:26 AM
 * Project : JSCash
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class JWTSecurity {

    private SecretKeySpec secretKey;

    private byte[] key;

    private void setKey() {
        MessageDigest sha = null;
        try {
            String myKey = "AIS_JSCASH";
            key = myKey.getBytes("UTF-8");
            sha = MessageDigest.getInstance("SHA-1");
            key = sha.digest(key);
            key = Arrays.copyOf(key, 32);
            secretKey = new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public String createJWT(String subject) {

        String id = "ffhocentral";
        String issuer = "faujiHo";
        int ttlMints = 15;

        // The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        // We will sign our JWT with our ApiKey secret
        setKey();
        Key signingKey = new SecretKeySpec(key, signatureAlgorithm.getJcaName());

        // Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id).setIssuedAt(new Date()).setSubject(subject).setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        // if it has been specified, let's add the expiration

        if (ttlMints >= 0) {
            Calendar calendar = Calendar.getInstance();
            // Add 15 minutes to the calendar time
            calendar.add(Calendar.MINUTE, ttlMints);
            builder.setExpiration(null);
        }

        // Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    @SuppressWarnings("unused")
    public String createJWTWithClaims(String subject, Map<String, Object> claims) {

        String id = "JS_CASH_MNG";
        String issuer = "AiS";
        int ttlMints = 23;

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        setKey();
        Key signingKey = new SecretKeySpec(key, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder().setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .setClaims(claims)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (ttlMints > 0) {
            Calendar calendar = Calendar.getInstance();
            // Add 15 minutes to the calendar time
            calendar.add(Calendar.MINUTE, ttlMints);
            builder.setExpiration(null);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();

    }

    public HashMap<String, String> parseJWT(String jwt) {
        try {
            AESencryption aeSencryption = new AESencryption();
            jwt = aeSencryption.decrypt(jwt);
            setKey();
            // This line will throw an exception if it is not a signed JWS (as expected)

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();

            HashMap<String, String> jwtmap = new HashMap<String, String>();


            jwtmap.put("ID", claims.getId());
            jwtmap.put("Subject", claims.getSubject());
            jwtmap.put("Issuer", claims.getIssuer());
            jwtmap.put("Expiration", claims.getExpiration() == null ? "" : claims.getExpiration().toString());
            jwtmap.put("Expired", "N");


            jwtmap.put("userId", String.valueOf(claims.get("userId")));
            jwtmap.put("loginId", String.valueOf(claims.get("loginId")));
            String userType = String.valueOf(claims.get("userTypeId"));
            jwtmap.put("userTypeId", userType);
            if (userType.equalsIgnoreCase("O")) {
                jwtmap.put("companyId", String.valueOf(claims.get("companyId")));
            } else {
                jwtmap.put("branchId", String.valueOf(claims.get("branchId")));

            }
            return jwtmap;
        } catch (Exception e) {
            HashMap<String, String> jwtmap = new HashMap<String, String>();
            System.out.println("\n\n" + e.getMessage());
            if (e.getMessage().contains("JWT expired at")) {

                jwtmap.put("Expired", "Y");
                return jwtmap;
            }
            return null;
        }
    }

    public HashMap<String, String> parseCLientSecretJWT(String jwt) {
        try {
            AESencryption aeSencryption = new AESencryption();
            jwt = aeSencryption.decrypt(jwt);
            setKey();
            // This line will throw an exception if it is not a signed JWS (as expected)

            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jwt).getBody();
            HashMap<String, String> jwtmap = new HashMap<String, String>();


            jwtmap.put("ID", claims.getId());
            jwtmap.put("Subject", claims.getSubject());
            jwtmap.put("Issuer", claims.getIssuer());
            jwtmap.put("Expiration", claims.getExpiration() == null ? "" : claims.getExpiration().toString());
            jwtmap.put("Expired", "N");


            return jwtmap;
        } catch (Exception e) {
            System.out.println("\n\n" + e.getMessage());
            if (e.getMessage().contains("JWT expired at")) {
                HashMap<String, String> jwtmap = new HashMap<String, String>();
                jwtmap.put("Expired", "Y");
                return jwtmap;
            }
            return null;
        }
    }


}
