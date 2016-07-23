/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author NavNag
 */
public class AppUtil {

    static final String HASH_SALT = "Cr0$s0v3r~n@g@";
    static Random RND = new Random();

    public long getCurrentTime() {
        return getTime(true, 0);
    }

    public long getPastTimeBy(int mins) {
        return getTime(false, mins);
    }

    public long getFutureTimeBy(int mins) {
        return getTime(true, mins);
    }

    private long getTime(boolean future, int mins) {
        return System.currentTimeMillis() + (future ? 1 : -1) * mins * 60 * 1000;
    }

    public String generateRandomHash() {
        return generateHash("" + RND.nextLong() + "" + System.currentTimeMillis());
    }

    public String generateHash(String input) {
        String hashtext = input + HASH_SALT;
        MessageDigest m;
        try {
            m = MessageDigest.getInstance("MD5");
            m.reset();
            m.update(input.getBytes());
            byte[] digest = m.digest();
            BigInteger bigInt = new BigInteger(1, digest);
            hashtext = bigInt.toString(16);
            while (hashtext.length() < 32) {
                hashtext = "0" + hashtext;
            }
        } catch (NoSuchAlgorithmException ex) {
            Logger.getLogger(AppUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hashtext;

    }

}
