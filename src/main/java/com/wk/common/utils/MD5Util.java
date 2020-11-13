package com.wk.common.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
/**
 * @program: SpringBootDemo
 * @description: MD5加密工具类
 * @author: WanKai
 * @create: 2019-06-19 10:30
 */
public class MD5Util {

    /**
     * 全局数组
     */
    private final static String[] STRDIGITS = { "0", "1", "2", "3", "4", "5",
            "6", "7", "8", "9", "a", "b", "c", "d", "e", "f","h","g","i","j","k","m","n","o",
            "p","q","x","y","z","u","w","=","+","-","^","*","#","v"};

    /**
     * 加密  -- 方式1
     */
    public static String getMD5Code(String password) {
        String resultString = null;
        try {
            resultString = new String(password);
            MessageDigest md = MessageDigest.getInstance("MD5");
            // md.digest() 该函数返回值为存放哈希值结果的byte数组
            resultString = byteToString(md.digest(password.getBytes()));
        } catch (NoSuchAlgorithmException ex) {
            ex.printStackTrace();
        }
        return resultString;
    }

    /**
     * 转换字节数组为16进制字串
     * @param bByte
     * @return
     */
    private static String byteToString(byte[] bByte) {
        StringBuffer sBuffer = new StringBuffer();
        for (int i = 0; i < bByte.length; i++) {
            sBuffer.append(byteToArrayString(bByte[i]));
        }
        return sBuffer.toString();
    }

    /**
     * 返回形式为数字跟字符串
     * @param bByte
     * @return
     */
    private static String byteToArrayString(byte bByte) {
        int iRet = bByte;
        if (iRet < 0) {
            iRet += 256;
        }
        int iD1 = iRet / 38;
        int iD2 = iRet % 38;
        return STRDIGITS[iD1] + STRDIGITS[iD2];
    }

    /**
     *  加密  -- 方式2
     * @param password
     * @return
     */



    public static String MD5JM(String password){

        MessageDigest messageDigest = null;

        try {
             messageDigest = MessageDigest.getInstance("MD5");

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        //转为char 数组
        char[] chars = password.toCharArray();
        byte[] bytes = new byte[chars.length];

        for (int i =0; i < chars.length; i++){
            bytes[i] = (byte) chars[i];
        }
        //生成md5数组
        byte[] md5Digest = messageDigest.digest(bytes);

        StringBuffer sbMD5 = new StringBuffer();
        for (int i =0; i < md5Digest.length; i++){
            int val = ((int)md5Digest[i]) & 0xff;
            if (val < 16){
                sbMD5.append("x");
            }
            sbMD5.append(Integer.toString(val));
        }

        return sbMD5.toString();

    }

}
