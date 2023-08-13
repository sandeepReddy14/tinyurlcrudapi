package com.example.tinyurlcrudapi.utils;

import java.math.BigInteger;

public class MD5ToBase62 {
    private static final char[] base62 = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789".toCharArray();
    private static final BigInteger val62 = BigInteger.valueOf(62);
    public static String md5bytesToBase62(byte[] md5Bytes)
    {
        BigInteger bigInteger = new BigInteger(md5Bytes).abs();
        StringBuilder base62String = new StringBuilder();
        int remainder = 0;
        while(!bigInteger.equals(BigInteger.ZERO))
        {
            remainder = bigInteger.mod(val62).intValue();
            bigInteger = bigInteger.divide(val62);
            base62String.append(base62[remainder]);
        }
        return base62String.toString();
    }
    public static String intToBase62(int val)
    {
        StringBuilder base62String = new StringBuilder();
        val = Math.abs(val);
        while(val > 0)
        {
            base62String.append(base62[val%62]);
            val = val/62;
        }
        return base62String.toString();
    }

    public static String longToBase62(long val)
    {
        StringBuilder base62String = new StringBuilder();
        val = Math.abs(val);
        while(val > 0)
        {
            base62String.append(base62[(int)(val%62)]);
            val = val/62;
        }
        return base62String.toString();
    }

}
