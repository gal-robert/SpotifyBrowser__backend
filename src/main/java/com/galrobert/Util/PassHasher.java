package com.galrobert.Util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.SecureRandom;

//insecure and unreliable as hell.
//Note to self: don't try this again. Like, ever.

public class PassHasher {

    private byte[] salt;
    private String password;

    public PassHasher() throws NoSuchProviderException, NoSuchAlgorithmException {

    }

    public static String hasher(String password) {
        String hashedPass = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(getSalt());
            byte[] bytes = md.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++) {
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            hashedPass = sb.toString();
        } catch (NoSuchAlgorithmException | NoSuchProviderException e ) {
            e.printStackTrace();
        }
        return hashedPass;
    }

    private static byte[] getSalt() throws NoSuchProviderException, NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG", "SUN");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }
}
