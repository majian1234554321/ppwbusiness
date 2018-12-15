package com.yjhh.ppwbusiness.utils;

import java.io.InputStream;
import java.security.MessageDigest;

public class Md5Util {
    /**
     * 根据输入流获得文件MD5
     *
     * @param inputStream 输入流
     * @return 字符串
     */
    public static String getMD5(InputStream inputStream) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            int numRead = 0;
            byte[] buffer = new byte[1024];
            while ((numRead = inputStream.read(buffer)) > 0) {
                md.update(buffer, 0, numRead);
            }
            return toHexString(md.digest());
        } catch (Exception e) {
            System.out.println(e.getStackTrace());
            return null;
        }
    }

    /**
     * 对字符串md5加密
     *
     * @param str 待加密字符串
     * @return md5值
     */
    public static String getMD5(String str) {
        String encodingType = "UTF-8";
        return getMD5(str, encodingType);
    }

    /**
     * 16/32 加密
     *
     * @param str
     * @param length
     * @return
     */
    public static String getMD5(String str, int length) {
        String text = getMD5(str);
        if (length == 16)
            return text.substring(8, 24);
        if (length == 8)
            return text.substring(12, 20);
        return text;
    }


    public static String getMD5(String str, String encodingType) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5"); // 生成一个MD5加密计算摘要
            md.update(str.getBytes(encodingType));
            return toHexString(md.digest());  //return new BigInteger(1, md.digest()).toString(16);
        } catch (Exception e) {
            System.out.println("MD5加密出现错误:" + e.getMessage());
            return "";
        }
    }

    /**
     * md5加密
     *
     * @param buf byte
     * @return String
     */
    public static String getMD5(byte[] buf) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(buf);
            return toHexString(md.digest());
        } catch (Exception e) {
            System.out.println("MD5加密出现错误:" + e.getMessage());
            return "";
        }
    }

    /**
     * 转16进制
     *
     * @param md 字节型数组
     * @return 字符串
     */
    public static String toHexString(byte[] md) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};
        int j = md.length;
        char str[] = new char[j * 2];
        for (int i = 0; i < j; i++) {
            byte byte0 = md[i];
            str[2 * i] = hexDigits[byte0 >>> 4 & 0xf];
            str[i * 2 + 1] = hexDigits[byte0 & 0xf];
        }
        return new String(str);
    }
}