package com.murui.applet.utils;

import org.apache.commons.codec.digest.DigestUtils;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;

public class MD5Utils {
    /**
     * 简单签名
     *
     * @param preStr
     * @param key
     * @param input_charset
     * @return java.lang.String
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 6:46 PM 6/29/2022
     **/
    public static String sign(String preStr, String key, String input_charset) {
        return DigestUtils.md5Hex(getContentBytes(preStr + key, input_charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if (charset != null && !charset.isEmpty()) {
            try {
                return content.getBytes(charset);
            } catch (UnsupportedEncodingException var3) {
                throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset, var3);
            }
        } else {
            return content.getBytes();
        }
    }

    public static boolean verify(String text, String sign, String key, String input_charset) {
        String mySign = DigestUtils.md5Hex(getContentBytes(text + key, input_charset));
        return mySign.equals(sign);
    }

    /**
     * MD5加码 生成32位md5码
     *
     * @param inStr
     * @return java.lang.String
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 6:47 PM 6/29/2022
     **/
    public static String string2MD5(String inStr) {
        MessageDigest md5 = null;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            System.out.println(e.toString());
            e.printStackTrace();
            return "";
        }
        char[] charArray = inStr.toCharArray();
        byte[] byteArray = new byte[charArray.length];
        for (int i = 0; i < charArray.length; i++)
            byteArray[i] = (byte) charArray[i];
        byte[] md5Bytes = md5.digest(byteArray);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    /**
     * 加密解密算法 执行一次加密，两次解密
     *
     * @param inStr
     * @return java.lang.String
     * @author masterzhang && masterzhangnb@gmail.com
     * @date 6:48 PM 6/29/2022
     **/
    public static String convertMD5(String inStr) {
        char[] a = inStr.toCharArray();
        for (int i = 0; i < a.length; i++) {
            a[i] = (char) (a[i] ^ 't');
        }
        String s = new String(a);
        return s;
    }

    public static void main(String args[]) {
        String s = new String("admin");
        System.out.println("原始：" + s);
        System.out.println("MD5后：" + string2MD5(s));
        System.out.println("加密的：" + convertMD5(s));
        System.out.println("解密的：" + convertMD5(convertMD5(s)));
    }
}
