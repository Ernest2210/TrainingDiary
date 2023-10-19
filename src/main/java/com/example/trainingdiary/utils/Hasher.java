package com.example.trainingdiary.utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Hasher {
    private static MessageDigest messageDigest = null;

    public static String hash(String str){
        if(messageDigest == null){
            try {
                messageDigest = MessageDigest.getInstance("SHA-256");
            } catch (NoSuchAlgorithmException e) {
                throw new RuntimeException(e);
            }
        }
        byte[] mdbytes = messageDigest.digest(str.getBytes(StandardCharsets.UTF_8));
        StringBuffer sb = new StringBuffer();

        for (int j = 0; j < mdbytes.length; j++) {
            String s = Integer.toHexString(0xff & mdbytes[j]);
            s = (s.length() == 1) ? "0" + s : s;
            sb.append(s);
        }
        return sb.toString();
    }
}
