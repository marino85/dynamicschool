/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ccit.ejb.utilidades;

import java.math.BigInteger;
import java.security.SecureRandom;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author marino
 */
public class EncrytUtil {

    private static SecureRandom random = new SecureRandom();

    public static String encrypPwd(String pwd) {
        String password = "";

        byte md5[] = DigestUtils.md5(pwd);
        byte base64[] = Base64.encodeBase64(md5);
        password = new String(base64);
        System.out.println(password);
        return password;
    }

    public static String nextSessionId() {
        return new BigInteger(60, random).toString(32);
    }

    public static void main(String[] args) {
        encrypPwd("admin");
    }
}
