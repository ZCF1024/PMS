package com.company.pms.pmsbase.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * @User zcf
 * @Create 2018/7/20 10:52
 * @Desc: MD5加密工具（密码）
 */
public class MD5Util {

    public static String getMD5(String password) {
        try {
            // 得到一个信息摘要器，MessageDigest提供信息摘要算法的功能，如 MD5 或 SHA 算法。
            MessageDigest digest = MessageDigest.getInstance("md5");

            // 得到密码的 16 为摘要信息
            byte[] result = digest.digest(password.getBytes());
            // System.out.println(result.length);
            StringBuffer buffer=new StringBuffer();

            // 把每一个 byte 做一个与运算 0xff(255)
            for (byte b : result) {
                int number = b & 0xff;
                String str = Integer.toHexString(number);
                if(str.length()==1){
                    buffer.append("0");
                }
                buffer.append(str);
            }

            // 标准的md5加密后的结果
            return buffer.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return "";
        }
    }

    private MD5Util(){ }

    public static void main(String[] args) {
        System.out.println(getMD5("201608040229"));
//        System.out.println(getMD5("201608040229" + "10465" + "9726"));
    }
}