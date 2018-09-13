package com.company.pms.pmsbase.utils;

import java.util.Random;
import java.util.UUID;

/**
 * @User zcf
 * @Create 2018/7/20 9:35
 * @Desc: UUIDUtil 是通用唯一识别码生成工具，UUID能保证对在同一时空中生成的数字都是唯一的
 */
public class UUIDUtils {
    /** 得到32位UUID */
    public static String getUUID() {
        /**
         * UUID的标准形式包含32个16进制数字，以连字号分为五段，形式为8-4-4-4-12的32个字符
         * 如：1bacef49-0c36-4636-a5a4-f73c9bf03cee
         * 用replaceAll将UUID randomUUID()返回的UUID中的 '-' 替换成空字符
         */
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    /** 得到4位UUID */
    public static String getUUID4() {
        return UUID.randomUUID().toString().split("-")[1];
    }

    /** 得到8位UUID */
    public static String getUUID8() {
        return UUID.randomUUID().toString().split("-")[0];
    }

    /**得到12位UUID */
    public static String getUUID12() {
        String []uuid = UUID.randomUUID().toString().split("-");
        return uuid[0]+uuid[1];
    }

    /** 得到16位UUID */
    public static String getUUID16() {
        String []uuid = UUID.randomUUID().toString().split("-");
        return uuid[0]+uuid[4];
    }

    public static void main(String[] args) {
        System.out.println("UUID32: " + UUIDUtils.getUUID());  //UUID
        System.out.println("UUID4: " + UUIDUtils.getUUID4());  //UUID
        System.out.println("UUID8: " + UUIDUtils.getUUID8());  //UUID
        System.out.println("UUID12: " + UUIDUtils.getUUID12());  //UUID
        System.out.println("UUID16: " + UUIDUtils.getUUID16());  //UUID
        System.out.println("自定义ID: " + UUIDUtils.getRandomId(32).toLowerCase());  //自定义ID生成器
    }

    private final static char[] digits = {'0', '1', '2', '3', '4', '5', '6', '7', '8',
            '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l',
            'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
            'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
            'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y',
            'Z'};

    /**
     * 随机ID生成器，由数字、小写字母和大写字母组成
     *
     * @param size 自定义ID长度
     * @return
     */
    public static String getRandomId(int size) {
        Random random = new Random();
        char[] tmpId = new char[size];
        for (int i = 0; i < size; i++) {
            tmpId[i] = digits[random.nextInt(digits.length)];
        }
        return new String(tmpId);
    }

    private UUIDUtils(){ }
}