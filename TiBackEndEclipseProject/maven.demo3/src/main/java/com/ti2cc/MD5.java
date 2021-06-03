package com.ti2cc;

import java.security.*;
import java.math.*;

public class MD5 {
    public static String main(String s) throws Exception{
       MessageDigest m=MessageDigest.getInstance("MD5");
       m.update(s.getBytes(),0,s.length());
       s = new BigInteger(1,m.digest()).toString(16);
       return s;
    }
}